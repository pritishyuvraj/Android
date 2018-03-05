import nltk 
from nltk.tokenize import TweetTokenizer 
import cal_calorie_given_food_name
import pickle
import datetime
import time 
from pprint import pprint 

# print calorie.cal_calorie("pears")
class food_Solution:
	def __init__(self):
		self.tknzr = TweetTokenizer()
		self.calorie = cal_calorie_given_food_name.food_to_calorie()
		self.food_names = self.read_file("/home/pritish/CCPP/Android/hackAmherst/python/data/food_desc_files/food_names.pickle")
		self.day_no_to_week = {
		0: 'Monday',
		1: 'Tuesday',
		2: 'Wednesday',
		3: 'Thursday',
		4: 'Friday',
		5: 'Saturday',
		6: 'Sunday'
		}
		# print self.food_names

	def which_meal(self, time):
		
		time = int(time)
		print time
		day_time  = ''
		if time >=0 and time <=10:
			day_time = 'Breakfast'
		elif time>10 and time <17:
			day_time = 'Lunch'
		else:
			day_time = 'Dinner'
		return day_time

	def user_speech(self, text, incoming_ip_add):
		text = text.split('.')
		self.database_found_food_keywords = {}
		print text
		for line in text:
			# line_text = self.tknzr.tokenize(line)
			text = ''
			line = line.lower()
			print line
			for food_word in self.food_names:
				food_word = food_word.lower()	
				if food_word in line:
				# if line.__contains__(' ' + food_word + ' '):
					# print food_word
					try:
						calorie_val = self.calorie.cal_calorie(food_word.lower())
					except:
						calorie_val = -1
					# if food_word or food_word.title() in self.database_found_food_keywords: continue
					food_word = food_word.title()

					self.database_found_food_keywords[food_word] = []	
					self.database_found_food_keywords[food_word].append(calorie_val)
					hours =  time.strftime("%H")
					weekday = self.day_no_to_week[datetime.datetime.today().weekday()]
					self.database_found_food_keywords[food_word].append(self.which_meal(hours) )
					self.database_found_food_keywords[food_word].append(weekday)
					text += str(incoming_ip_add) + ","
					text += str(food_word) + ","
					text += str(calorie_val) + ","
					text += str(self.which_meal(hours)) + ","
					text += str(weekday) + "\n"
					self.append_to_file("database.csv", text)

		pprint(self.database_found_food_keywords)

		return self.database_found_food_keywords
	
	def append_to_file(self, fileName, var):
		with open(fileName, "a") as myfile:
			myfile.write(var)

	def read_file(self, loc):
		with open(loc, "r") as f:
			return pickle.load(f)

if __name__ == '__main__':
	fs = food_Solution()
	fs.user_speech("Hi I am craving to eat chicken and Pancakes. It's probably the best dish in town. I wish to eat rice too", "fake")
