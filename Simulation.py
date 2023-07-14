import csv
import time
import pyrebase
from collections.abc import Mapping, MutableMapping

config = {
  "apiKey": "AIzaSyC5jOsG3VGJfRmQyLfPA2KDL-Nr4xtT3vU",
  "authDomain": "floodthing-c588b.firebaseapp.com",
  "databaseURL": "https://floodthing-c588b-default-rtdb.firebaseio.com/",
  "projectId": "floodthing-c588b",
  "storageBucket": "floodthing-c588b.appspot.com",
  "messagingSenderId": "376177091662",
  "appId": "1:376177091662:web:fba400fc24e4d6cd744f1f",
  "measurementId": "G-E39D1GDQRJ"
};

firebase = pyrebase.initialize_app(config)
storage = firebase.storage()
database = firebase.database()

# Open the CSV file
with open('SDN\Waterlevel.csv', 'r') as csvfile:
    # Create a CSV reader object
    csvreader = csv.reader(csvfile)
    
    # Skip the header row
    next(csvreader)
    i = 2
    # Loop through the data and simulate a timeline
    while i < 15:
        for row in csvreader:
            coord = row[1]
            Loc = row[0]
            water_lvl = row[i]
            # Print the timestamp and data
            print(f'Loc: {coord} | Waterlevel: {water_lvl}')
            data = {
                "Location" : coord,
                "Place" : Loc,
                "Distance" : water_lvl
            }
            database.set(data)
            
            # Wait for 2 seconds before continuing
            time.sleep(10)
        i += 1
        csvfile.seek(0)
        next(csvreader)
        
        # Wait for 2 seconds before continuing to the next row
        time.sleep(2)
    