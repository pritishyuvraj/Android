#!flask/bin/python

from flask import Flask, jsonify
from flask import abort 
from flask import make_response 
from flask import request

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

@app.route('/todo', methods = ['GET'])
def get_tasks():
	# return jsonify({'tasks':tasks})
	return "Hello friends"


@app.route('/todo/api/v1.0/tasks/<int:task_id>', methods = ['GET'])
def get_task(task_id):
	task = [task for task in tasks if task['id'] == task_id]
	if len(task) == 0:
		abort(404)

	return jsonify({'task':task[0]})

@app.errorhandler(404)
def not_found(error):
	print "Not found"
	return make_response(jsonify({'error':'Not found'}), 404)

@app.route('/todo/api/v1.0/tasks', methods = ['POST'])
def create_task():
	print "Request -> ", request.json, request.values
	print "TYpe -> ", type(request.values), request.values.values(), request.values.keys()
	return "Hello Everyone"
		#temp = request.values
	#print temp[0]
	#print "Request 2 -> ", request.stream.readlines(),  dir(request.stream)#, dir(request)
	if not request.json or not 'title' in request.json:
		#return jsonify({'task':tasks})
		abort(400)
	task = {
	'id':tasks[-1]['id'] + 1,
	'title':request.json['title'],
	'description':request.json.get('description', ""),
	'done':False
	}
	tasks.append(task)
	#print tasks
	return jsonify({'task':task}), 201

@app.route('/todo/api/v1.0/tasks/<int:task_id>', methods=['PUT'])
def update_task(task_id):
    task = [task for task in tasks if task['id'] == task_id]
    if len(task) == 0:
        abort(404)
    if not request.json:
        abort(400)
    if 'title' in request.json and type(request.json['title']) != unicode:
        abort(400)
    if 'description' in request.json and type(request.json['description']) is not unicode:
        abort(400)
    if 'done' in request.json and type(request.json['done']) is not bool:
        abort(400)
    task[0]['title'] = request.json.get('title', task[0]['title'])
    task[0]['description'] = request.json.get('description', task[0]['description'])
    task[0]['done'] = request.json.get('done', task[0]['done'])
    return jsonify({'task': task[0]})


@app.route('/todo/api/v1.0/tasks/<int:task_id>', methods=['DELETE'])
def delete_task(task_id):
    task = [task for task in tasks if task['id'] == task_id]
    # task = [1, 2]
    if len(task) == 0:
        abort(404)
    tasks.remove(task[0])
    return jsonify({'result': task_id})



if __name__ == '__main__':	
	app.run(host = "0.0.0.0", debug = True, port = 8000)

	#Commands for transmitting data using RESTful API
	# curl -i -H "Content-Type: application/json" -X POST -d '{"title": "Read a book"}' http://localhost:5000/todo/api/v1.0/tasks
	# curl -i http://localhost:5000/todo/api/v1.0/tasks/1
	# curl -i http://localhost:5000/todo/api/v1.0/tasks
	# curl -i -H "Content-Type: application/json" -X PUT -d '{"done":true}' http://localhost:5000/todo/api/v1.0/tasks/2
	# curl -i -H "Content-Type: application/json" -X DELETE  http://localhost:5000/todo/api/v1.0/tasks/2
