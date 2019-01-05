import tensorflow as tf

input = tf.placeholder(tf.float32, shape=[None, 3], name='input')
W = tf.Variable(tf.zeros(shape=[3, 2]), dtype=tf.float32, name="weight")
b = tf.Variable(tf.zeros(shape=[2]), dtype=tf.float32, name="bias")
output = tf.nn.relu(tf.matmul(input, W) + b, name="output")

saver = tf.train.Saver()
init_op = tf.global_variables_initializer()

with tf.Session() as sess:
    sess.run(init_op)

    # To save the graph
    tf.train.write_graph(sess.graph_def, ".", "tfdroid.pbtxt")

    # Assigning some random value to w
    sess.run(tf.assign(W, [[1, 2], [4, 5], [7, 8]]))
    sess.run(tf.assign(b, [1, 1]))

    # Save the checkpoint file
    saver.save(sess, './tfdroid.ckpt')
