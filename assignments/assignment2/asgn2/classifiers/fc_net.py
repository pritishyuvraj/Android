import numpy as np

from asgn2.layers import *
from asgn2.layer_utils import *


class TwoLayerNet(object):
  """
  A two-layer fully-connected neural network with ReLU nonlinearity and
  softmax loss that uses a modular layer design. We assume an input dimension
  of D, a hidden dimension of H, and perform classification over C classes.

  The architecure should be affine - relu - affine - softmax.

  Note that this class does not implement gradient descent; instead, it
  will interact with a separate Solver object that is responsible for running
  optimization.

  The learnable parameters of the model are stored in the dictionary
  self.params that maps parameter names to numpy arrays.
  """

  def __init__(self, input_dim=3*32*32, hidden_dim=100, num_classes=10,
               weight_scale=1e-3, reg=0.0):
    """
    Initialize a new network.

    Inputs:
    - input_dim: An integer giving the size of the input
    - hidden_dim: An integer giving the size of the hidden layer
    - num_classes: An integer giving the number of classes to classify
    - dropout: Scalar between 0 and 1 giving dropout strength.
    - weight_scale: Scalar giving the standard deviation for random
      initialization of the weights.
    - reg: Scalar giving L2 regularization strength.
    """
    self.params = {}
    self.reg = reg

    ############################################################################
    # TODO: Initialize the weights and biases of the two-layer net. Weights    #
    # should be initialized from a Gaussian with standard deviation equal to   #
    # weight_scale, and biases should be initialized to zero. All weights and  #
    # biases should be stored in the dictionary self.params, with first layer  #
    # weights and biases using the keys 'W1' and 'b1' and second layer weights #
    # and biases using the keys 'W2' and 'b2'.                                 #
    ############################################################################
    self.params['W1'] = np.random.normal(scale = weight_scale, size = [input_dim, hidden_dim])
    self.params['W2'] = np.random.normal(scale = weight_scale, size = [hidden_dim, num_classes])
    self.params['b1'] = np.zeros([hidden_dim])
    self.params['b2'] = np.zeros([num_classes])
    ############################################################################
    #                             END OF YOUR CODE                             #
    ############################################################################


  def loss(self, X, y=None):
    """
    Compute loss and gradient for a minibatch of data.

    Inputs:
    - X: Array of input data of shape (N, d_1, ..., d_k)
    - y: Array of labels, of shape (N,). y[i] gives the label for X[i].

    Returns:
    If y is None, then run a test-time forward pass of the model and return:
    - scores: Array of shape (N, C) giving classification scores, where
      scores[i, c] is the classification score for X[i] and class c.

    If y is not None, then run a training-time forward and backward pass and
    return a tuple of:
    - loss: Scalar value giving the loss
    - grads: Dictionary with the same keys as self.params, mapping parameter
      names to gradients of the loss with respect to those parameters.
    """
    scores = None
    ############################################################################
    # TODO: Implement the forward pass for the two-layer net, computing the    #
    # class scores for X and storing them in the scores variable.              #
    ############################################################################
    X_reshaped = X.reshape(X.shape[0], -1)
    # print X_reshaped.shape, self.params['W1'].shape
    pass1 = np.maximum(0.0, X_reshaped.dot(self.params['W1']) + self.params['b1'])
    scores = pass1.dot(self.params['W2']) + self.params['b2']
    # scores = np.exp(scores)
    # for index, line in enumerate(scores):
    #   scores[index] = [line[i]/np.sum(line) for i in xrange(len(line))]

    ############################################################################
    #                             END OF YOUR CODE                             #
    ############################################################################

    # If y is None then we are in test mode so just return scores
    if y is None:
      return scores

    loss, grads = 0, {}
    ############################################################################
    # TODO: Implement the backward pass for the two-layer net. Store the loss  #
    # in the loss variable and gradients in the grads dictionary. Compute data #
    # loss using softmax, and make sure that grads[k] holds the gradients for  #
    # self.params[k]. Don't forget to add L2 regularization!                   #
    #                                                                          #
    # NOTE: To ensure that your implementation matches ours and you pass the   #
    # automated tests, make sure that your L2 regularization includes a factor #
    # of 0.5 to simplify the expression for the gradient.                      #
    ############################################################################
    
    #Computing Softmax
    # exp_scores = np.exp(scores)
    # scores = exp_scores / np.sum(exp_scores, axis = 1).reshape(scores.shape[0], 1)

    loss, d_y = softmax_loss(scores, y)
    # print np.sum(self.params['W1']*self.params['W1'])
    loss += 0.5 *self.reg*np.sum(self.params['W1'] * self.params['W1']) + 0.5*self.reg *np.sum( self.params['W2']*self.params['W2'])
    # print loss

    # print d_y.shape, pass1.shape, self.params['W2'].shape
    grads['W2'] = pass1.T.dot(d_y)
    grads['W2'] += self.reg*self.params['W2']
    # print self.params['b2'].shape, d_y.shape
    grads['b2'] = np.sum(d_y.T, axis = 1)
    
    pass1_derivative = d_y.dot(self.params['W2'].T)
    pass1_derivative[pass1<0] = 0.0

    # print "This", self.params['W1'].shape, X_reshaped.shape, pass1_derivative.shape
    grads['W1'] = X_reshaped.T.dot(pass1_derivative)
    grads['W1'] += self.reg*self.params['W1']
    # print grads['W1'].shape, pass1.shape
    # grads['W1'][pass1<0] = 0.0
    # grads['b2'] 
    grads['b1'] = np.sum(pass1_derivative.T, axis = 1)

    
    
    
    ############################################################################
    #                             END OF YOUR CODE                             #
    ############################################################################

    return loss, grads


class FullyConnectedNet(object):
  """
  A fully-connected neural network with an arbitrary number of hidden layers,
  ReLU nonlinearities, and a softmax loss function. This will also implement
  dropout and batch normalization as options. For a network with L layers,
  the architecture will be

  {affine - [batch norm] - relu - [dropout]} x (L - 1) - affine - softmax

  where batch normalization and dropout are optional, and the {...} block is
  repeated L - 1 times.

  Similar to the TwoLayerNet above, learnable parameters are stored in the
  self.params dictionary and will be learned using the Solver class.
  """

  def __init__(self, hidden_dims, input_dim=3*32*32, num_classes=10,
               dropout=0, use_batchnorm=False, reg=0.0,
               weight_scale=1e-2, dtype=np.float32, seed=None):
    """
    Initialize a new FullyConnectedNet.

    Inputs:
    - hidden_dims: A list of integers giving the size of each hidden layer.
    - input_dim: An integer giving the size of the input.
    - num_classes: An integer giving the number of classes to classify.
    - dropout: Scalar between 0 and 1 giving dropout strength. If dropout=0 then
      the network should not use dropout at all.
    - use_batchnorm: Whether or not the network should use batch normalization.
    - reg: Scalar giving L2 regularization strength.
    - weight_scale: Scalar giving the standard deviation for random
      initialization of the weights.
    - dtype: A numpy datatype object; all computations will be performed using
      this datatype. float32 is faster but less accurate, so you should use
      float64 for numeric gradient checking.
    - seed: If not None, then pass this random seed to the dropout layers. This
      will make the dropout layers deteriminstic so we can gradient check the
      model.
    """
    self.use_batchnorm = use_batchnorm
    self.use_dropout = dropout > 0
    self.reg = reg
    self.num_layers = 1 + len(hidden_dims)
    self.dtype = dtype
    self.params = {}

    ############################################################################
    # TODO: Initialize the parameters of the network, storing all values in    #
    # the self.params dictionary. Store weights and biases for the first layer #
    # in W1 and b1; for the second layer use W2 and b2, etc. Weights should be #
    # initialized from a normal distribution with standard deviation equal to  #
    # weight_scale and biases should be initialized to zero.                   #
    #                                                                          #
    # When using batch normalization, store scale and shift parameters for the #
    # first layer in gamma1 and beta1; for the second layer use gamma2 and     #
    # beta2, etc. Scale parameters should be initialized to one and shift      #
    # parameters should be initialized to zero.                                #
    ############################################################################
    # for i in xrange()
    # self.params['W1'] = np.random.normal(scale = weight_scale, size = ())
    # self.params['W2']
    # self.params['b1']
    # self.params['b2']

    for i in xrange(len(hidden_dims)+1):
        if i ==0:
            left = input_dim
            right = hidden_dims[i]
        elif i-1 == len(hidden_dims) - 1:
            right = num_classes
            left = hidden_dims[i-1]
        else:
            left = hidden_dims[i-1]
            right = hidden_dims[i]
        weight = 'W'+str(i+1)
        bias = 'b' + str(i+1)
        self.params[weight] = np.random.normal(scale = weight_scale, size = (left, right))
        self.params[bias] = np.zeros(right)
        if self.use_batchnorm and i != len(hidden_dims):
            self.params['gamma'+str(i+1)] = np.ones(right)
            self.params['beta'+str(i+1)] = np.zeros(right)
    # print self.params.keys()
    # print self.params.keys()
    ############################################################################
    #                             END OF YOUR CODE                             #
    ############################################################################

    # When using dropout we need to pass a dropout_param dictionary to each
    # dropout layer so that the layer knows the dropout probability and the mode
    # (train / test). You can pass the same dropout_param to each dropout layer.
    self.dropout_param = {}
    if self.use_dropout:
      self.dropout_param = {'mode': 'train', 'p': dropout}
      if seed is not None:
        self.dropout_param['seed'] = seed

    # With batch normalization we need to keep track of running means and
    # variances, so we need to pass a special bn_param object to each batch
    # normalization layer. You should pass self.bn_params[0] to the forward pass
    # of the first batch normalization layer, self.bn_params[1] to the forward
    # pass of the second batch normalization layer, etc.
    self.bn_params = []
    if self.use_batchnorm:
      self.bn_params = [{'mode': 'train'} for i in xrange(self.num_layers - 1)]

    # Cast all parameters to the correct datatype
    for k, v in self.params.iteritems():
      self.params[k] = v.astype(dtype)


  def loss(self, X, y=None):
    """
    Compute loss and gradient for the fully-connected net.

    Input / output: Same as TwoLayerNet above.
    """
    X = X.astype(self.dtype)
    mode = 'test' if y is None else 'train'

    # Set train/test mode for batchnorm params and dropout param since they
    # behave differently during training and testing.
    if self.dropout_param is not None:
      self.dropout_param['mode'] = mode
    if self.use_batchnorm:
      for bn_param in self.bn_params:
        bn_param['mode'] = mode

    scores = {}
    ############################################################################
    # TODO: Implement the forward pass for the fully-connected net, computing  #
    # the class scores for X and storing them in the scores variable.          #
    #                                                                          #
    # When using dropout, you'll need to pass self.dropout_param to each       #
    # dropout forward pass.                                                    #
    #                                                                          #
    # When using batch normalization, you'll need to pass self.bn_params[0] to #
    # the forward pass for the first batch normalization layer, pass           #
    # self.bn_params[1] to the forward pass for the second batch normalization #
    # layer, etc.                                                              #
    ############################################################################
    #FeedForward network 
    # X_reshaped = X.reshape(X.shape[0], -1)
    # for i in xrange(self.num_layers):
    #     if i == 0:
    #         # print i, X.shape, self.params['W1'].shape
    #         scores[i] = np.maximum(0, X_reshaped.dot(self.params['W1'] + self.params['b1']))
    #     elif i != self.num_layers - 1:
    #         # print i, scores[i-1].shape, self.params['W' + str(i+1)].shape
    #         scores[i] = np.maximum(0, 
    #             scores[i-1].dot(self.params['W'+str(i+1)]) + self.params['b' + str(i+1)])
    #     else:
    #         #Softmax layer 
    #         pass
    #         # print i, scores[i-1].shape, self.params['W' + str(i+1)].shape
    #         scores[i] = scores[i-1].dot(
    #             self.params['W' + str(i+1)] + self.params['b' + str(i+1)])
    cache_store = {}
    cacheReLU_store = {}
    cacheBatch_dict = {}
    cacheDropout_dict = {}
    input_to_network = X
    # print "one iteration done", self.params.keys()
    for i in xrange(self.num_layers - 1):
        # print "i -> ", i,
        
        # print "x, w, b", input_to_network.shape, self.params['W'+str(i+1)].shape, self.params['b'+str(i+1)].shape
        # print i, self.params['W'+str(i+1)].shape#, self.params['b'+str(i+1)].shape
        x1, cache = affine_forward(input_to_network, self.params['W'+str(i+1)], self.params['b'+str(i+1)])
        cache_store["cache"+str(i+1)] = cache
        # print x1.shape, self.params['gamma'+str(i+1)].shape, self.params['beta'+str(i+1)].shape
        if self.use_batchnorm:
            x1, cacheBatch_dict["cacheBatch"+str(i+1)] = batchnorm_forward(x1, \
                gamma=self.params['gamma'+str(i+1)], beta=self.params['beta'+str(i+1)], \
                bn_param = self.bn_params[i-1])
        if self.use_dropout:
            x1, cacheDropout_dict["cacheDropout"+str(i+1)] = dropout_forward(x1, \
                self.dropout_param)
        # print x1.shape
        input_to_network, cacheReLU = relu_forward(x1)
        cacheReLU_store["cacheReLU"+str(i+1)] = cacheReLU
        # print self.params['b'+str(self.num_layers)]
    scores, cache_store['cache'+str(self.num_layers)] = affine_forward(input_to_network, self.params['W'+str(self.num_layers)], self.params['b'+str(self.num_layers)])
    # print "one iteration done", self.params.keys()

    ############################################################################
    #                             END OF YOUR CODE                             #
    ############################################################################

    # If test mode return early
    if mode == 'test':
      return scores

    loss, grads = 0.0, {}
    ############################################################################
    # TODO: Implement the backward pass for the fully-connected net. Store the #
    # loss in the loss variable and gradients in the grads dictionary. Compute #
    # data loss using softmax, and make sure that grads[k] holds the gradients #
    # for self.params[k]. Don't forget to add L2 regularization!               #
    #                                                                          #
    # When using batch normalization, you don't need to regularize the scale   #
    # and shift parameters.                                                    #
    #                                                                          #
    # NOTE: To ensure that your implementation matches ours and you pass the   #
    # automated tests, make sure that your L2 regularization includes a factor #
    # of 0.5 to simplify the expression for the gradient.                      #
    ############################################################################
    #Softmax loss 
    # layers = scores.keys()
    # loss, d_y = softmax_loss(scores[layers[-1]], y)
    # for i in xrange(1, self.num_layers + 1):
    #     # print self.params['W' + str(i)].shape
    #     loss += 0.5*self.reg*np.sum((self.params['W' + str(i)]**2))

    # # print scores.keys()
    # # print "X", X_reshaped.shape, self.params['W1'].shape
    # # print "for y2", scores[0].shape, self.params['W2'].shape
    # # print "for y3", scores[1].shape, self.params['W3'].shape
    # for i in xrange(self.num_layers-1, -1, -1):
    #     # print "i", i
    #     if i == self.num_layers-1:
    #         #Softmax Layer
    #         # print self.params['W3'].shape
    #         grads['W'+str(i+1)] = scores[i-1].T.dot(d_y)
    #         # print self.params['b' + str(i+1)].shape, d_y.shape
    #         grads['b' + str(i+1)] = np.sum(d_y, axis = 0)

    #         #Regularization 
    #         grads['W'+ str(i+1)] += self.reg*self.params['W'+ str(i+1)]

    #         temp_dy_iG_dy_iS = d_y.dot(self.params['W' + str(i+1)].T)
    #         # print self.params['W'+str(i+1)].shape
    #     else:
    #         pass
    #         #RELU Lyers
    #         # print i, temp_dy_iG_dy_iS.shape, scores[i-1].shape, self.params['b'+str(i+1)].shape
    #         temp_dy_iG_dy_iS[scores[i] < 0.0]  = 0
    #         if i-1 >= 0:
    #             y1 = scores[i-1]
    #         else:
    #             y1 = X_reshaped
    #         grads['W'+str(i+1)] = temp_dy_iG_dy_iS.T.dot(y1).T
    #         grads['W'+str(i+1)] += self.reg*self.params['W' + str(i+1)]
    #         grads['b'+str(i+1)] = np.sum(temp_dy_iG_dy_iS, axis = 0)

    #         temp_dy_iG_dy_iS = temp_dy_iG_dy_iS.dot(self.params['W'+str(i+1)].T)
            # grads['W'+str(i+1)] = 
            # a, b, c = affine_backward(d_y, scores[i-1])
            # grads['W3'] = b 
            # grads['b3'] = c 
            # grads['b' + str(i+1)] = 
        # print i, layers
        # print d_y.shape, scores[layers[i-1]].shape
        

    #Using functions
    loss, d_y = softmax_loss(scores, y)
    temp_back_prop = d_y
    # print cacheReLU_store
    for i in xrange(1, self.num_layers+1):
        loss += 0.5*self.reg*np.sum(self.params['W'+str(i)]**2)
    dy_yi_yj, dW, db = affine_backward(d_y, cache_store["cache"+str(self.num_layers)])
    dW += self.reg*self.params['W'+str(i)]
    grads['W'+str(self.num_layers)] = dW 
    grads['b'+str(self.num_layers)] = db 
    l = self.num_layers-1
    for i in xrange(self.num_layers-1):
        if self.use_dropout:
            dy_yi_yj = dropout_backward(dy_yi_yj, cacheDropout_dict["cacheDropout"+str(l-i)])
        temp_back_prop = relu_backward(dy_yi_yj, cacheReLU_store["cacheReLU"+str(l-i)])
        if self.use_batchnorm:
            temp_back_prop, dgamma, dbeta = batchnorm_backward(temp_back_prop, cacheBatch_dict["cacheBatch"+str(l-i)])
            grads['gamma'+str(l-i)] = dgamma
            grads['beta'+str(l-i)] = dbeta
        dy_yi_yj, dW, db = affine_backward(temp_back_prop, cache_store["cache"+str(l-i)])
        grads["W"+str(l-i)] = dW + self.reg*self.params['W'+str(l-i)]
        grads['b'+str(l-i)] = db
    # print loss
    ############################################################################
    #                             END OF YOUR CODE                             #
    ############################################################################

    return loss, grads
