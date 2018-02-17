#!flask/bin/python

from flask import Flask, jsonify

app = Flask(__name__)

tasks = [
{
	'id':1, 
	'title':'Buy Groceries',
	'description':'Milk,Cheese,Pizza,Tylenol',
	'done':False 
},
{
	'id':2,
	'title':'Learn Python',
	'description':'Need to find a good python tutorial',
	'done':False
}
]

@app.route('/todo/api/v1.0/tasks', methods = ['GET'])
def get_tasks():
	return jsonify({'tasks':tasks})

if __name__ == '__main__':
	app.run(debug = True)