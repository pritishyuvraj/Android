#!flask/bin/python

from flask import Flask
from flask import jsonify
from flask import abort 
from flask import make_response
from flask import request 

app = Flask(__name__)

tasks = {
	"team_A": {
		"medals": {
			"bronze": 0,
			"gold": 2,
			"silver": 5
		}
	},

	"team_B": {
		"medals": {
			"bronze": 1,
			"gold": 20,
			"silver": 50
		}
	}
}

print tasks.keys()
print tasks.values()
print tasks['team_A']['medals']['gold']
#print {tasks:}

@app.route('/getMedalTally/', methods = ['GET'])
def get_task():
	return jsonify({'tasks':tasks})

@app.route('/getMedalTally/<string:teamName>', methods = ['GET'])
def get_tasks(teamName):
	return jsonify({'tasks':tasks[teamName]})

@app.route('/incrementMedalTally/<string:teamName>/<string:medalType>/<int:auth_id>', methods = ['GET'])
def increase_medal_tally(teamName, medalType, auth_id):
	if auth_id == 10:
		try:
			tasks[teamName]['medals'][medalType] += 1
			return jsonify({'tasks':tasks[teamName]['medals'][medalType]})
		except:
			abort(404)
	else:
		print "Fake access	"
		abort(404)

@app.errorhandler(404)
def not_found(error):
	return make_response(jsonify({'error':'You are not authorized to access!'}), 404)

if __name__ == '__main__':
	app.run(port = 8000)
	