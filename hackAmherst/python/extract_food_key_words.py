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
	def user_speech(self, text):
		text = text.split('.')
		self.database_found_food_keywords = {}
		for line in text:
			# line_text = self.tknzr.tokenize(line)
			for food_word in self.food_names:
				if food_word in line:
					# print food_word
					try:
						calorie_val = self.calorie.cal_calorie(food_word.lower())
					except:
						calorie_val = -1
					self.database_found_food_keywords[food_word] = []	
					self.database_found_food_keywords[food_word].append(calorie_val)
					hours =  time.strftime("%H:%M")
					weekday = self.day_no_to_week[datetime.datetime.today().weekday()]
					self.database_found_food_keywords[food_word].append(hours)
					self.database_found_food_keywords[food_word].append(weekday)
		# pprint(self.database_found_food_keywords)
		return self.database_found_food_keywords
		


	def read_file(self, loc):
		with open(loc, "r") as f:
			return pickle.load(f)

if __name__ == '__main__':
	fs = food_Solution()
	fs.user_speech("Hi I am craving to eat chicken. It's probably the best dish in town. I wish to eat rice too")
