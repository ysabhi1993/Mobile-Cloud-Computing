import csv
import pyrebase
config = {
  "apiKey": "apiKey",
  "authDomain": "tagme-5f2c4.firebaseapp.com",
  "databaseURL": "https://tagme-5f2c4.firebaseio.com/",
  "storageBucket": "bookshelf-164902.appspot.com"
}

firebase = pyrebase.initialize_app(config)
#auth = firebase.auth()
#user = auth.sign_in_with_email_and_password("tagmeappmcc@gmail.com", "tagme123456")
db = firebase.database()
all_users = db.child("/users").get()

myfile = open("alldata.csv","wb")

for i in all_users.each():
	with open("alldata.csv","a") as myfile:
		print("reading from firebase database.....")
		w = csv.DictWriter(myfile, i.val().keys(), quoting = csv.QUOTE_ALL)
	    	w.writerow(i.val())

