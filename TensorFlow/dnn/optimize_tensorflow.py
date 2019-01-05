import tensorflow as tf
from tensorflow.python.tools import optimize_for_inference_lib
MODEL_NAME = 'tfdroid'
output_frozen_graph_name = "frozen_" + MODEL_NAME + '.pb'
output_optimized_graph_name = "optimized_" + MODEL_NAME + '.pb'

input_graph_def = tf.GraphDef()

with tf.gfile.Open(output_frozen_graph_name, "rb") as f:
    data = f.read()
    input_graph_def.ParseFromString(data)

output_graph_def = optimize_for_inference_lib.optimize_for_inference(
    input_graph_def,
    ["input"],
    ["output"],
    tf.float32.as_datatype_enum
)

f = tf.gfile.FastGFile(output_optimized_graph_name, "w")
f.write(output_graph_def.SerializeToString())
