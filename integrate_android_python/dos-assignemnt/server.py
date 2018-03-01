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


#Winter Sports
sports = {
	"game1":{"team_A":0, "team_B":0},
	"game2":{"team_A":0, "team_B":0},
	"game3":{"team_A":0, "team_B":0}
}

@app.route('/getScore/', methods = ['GET'])
def get_winter_sports():
	return jsonify({'tasks':sports})

@app.route('/setScore/<string:sports_name>/<int:team_A_score>/<int:team_B_score>/<int:auth_id>', methods = ['GET'])
def set_scores(sports_name, team_A_score, team_B_score, auth_id):
	#
	print "Entered", auth_id
	if auth_id == 10:
		#print "came here"
		sports[sports_name]["team_A"] = team_A_score
		sports[sports_name]["team_B"] = team_B_score
		#print sports[sports_name]
		return jsonify({'tasks':sports[sports_name]})
	else:
		abort(404)

@app.route('/getScore/<string:sports_name>', methods = ['GET'])
def get_winter_scores(sports_name):
	#
	return jsonify({'tasks':sports[sports_name]})

@app.errorhandler(404)
def not_found(error):
	return make_response(jsonify({'error':'You are not authorized to access!'}), 404)

if __name__ == '__main__':
	app.run(port = 8000)
	