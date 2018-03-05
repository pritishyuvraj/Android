from flask import Flask

from flask import jsonify
from flask import abort 
from flask import make_response 
from flask import request
import time 
import extract_food_key_words
food_key = extract_food_key_words.food_Solution()

app = Flask(__name__)

@app.route("/")
def hello():
    return "How are you doingfasdasdasdasd"

@app.route('/posts/<string:temp_task>')
def dummy(temp_task):
	# return jsonify({"id":1, "userId":2, "title":"Hello mom", "body":"sdasdasdasd"})
	print "Look here", temp_task,"\n"
	database = food_key.user_speech(temp_task, request.remote_addr)
	print "\n\n", database, "\n\n"
	# time.sleep(5)
	print "\n\n Important info ", request.remote_addr
	return_array = []
	for one_item in database:
		temp_dic = {}
		print "See here", one_item, database[one_item]
		temp_dic["food_name"] = one_item
		temp_dic["calorie"] = database[one_item][0]
		temp_dic['time'] = database[one_item][1]
		temp_dic['day'] = database[one_item][2]
		print temp_dic
		return_array.append(temp_dic)
	print "\n\nOutput readY", return_array, "\n\n"
	return jsonify(return_array)   

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8080)