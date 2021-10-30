# Lab 10

## Student information
* Full name: Alp Can Aloglu
* E-mail: aalog001@ucr.edu
* UCR NetID: aalog001
* Student ID: 862190749

## Answers
* (Q1) Import the sample file into a new collection named `contacts`. Hint: Use the [`mongoimport`](https://docs.mongodb.com/database-tools/mongoimport/) command.
```text
mongoimport --db db --collection contacts --file /home/alp/workspace/aalog001_lab10/contacts.json --jsonArray
```
* (Q2) Retrieve all the users sorted by name.
```text
> db.contacts.find().sort({Name: 1})
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e6"), "Name" : "Aguirre Fox", "Address" : { "StreetNumber" : 540, "streetName" : "High Street", "city" : "Bloomington", "state" : "SC", "ZIPCode" : 29823 }, "Friends" : [ "Glenn Mcbride", "Marlene Macias", "Constance Arnold", "Beard Dotson", "Hester Lowe" ], "Active" : true, "DOB" : "Sat Mar 15 2014 06:04:01 GMT+0000 (UTC)", "Age" : 49 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ed"), "Name" : "Aimee Mcintosh", "Address" : { "StreetNumber" : 145, "streetName" : "Boardwalk ", "city" : "Mahtowa", "state" : "MA", "ZIPCode" : 35051 }, "Friends" : [ "Chase Wyatt", "Kelly Hewitt", "Michael Rodriguez" ], "Active" : false, "DOB" : "Sun Mar 10 1996 19:54:41 GMT+0000 (UTC)", "Age" : 38 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e8"), "Name" : "Cooke Schroeder", "Address" : { "StreetNumber" : 246, "streetName" : "Huntington Street", "city" : "Allendale", "state" : "NH", "ZIPCode" : 64947 }, "Friends" : [ "Rebekah Winters", "Grace Lewis", "Stephanie Hyde" ], "Active" : false, "DOB" : "Wed Oct 19 2016 15:38:31 GMT+0000 (UTC)", "Age" : 32 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ee"), "Name" : "Craft Parks", "Address" : { "StreetNumber" : 397, "streetName" : "Hudson Avenue", "city" : "Glenbrook", "state" : "UT", "ZIPCode" : 96867 }, "Friends" : [ "Love Short", "Dickerson Brock", "Berg Levy", "Lottie Pickett" ], "Active" : false, "DOB" : "Tue May 21 1996 13:17:58 GMT+0000 (UTC)", "Age" : 42 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ef"), "Name" : "Hayes Weaver", "Address" : { "StreetNumber" : 722, "streetName" : "Diamond Street", "city" : "Belva", "state" : "OH", "ZIPCode" : 90952 }, "Friends" : [ "Cecilia Oneill", "Trujillo Wilkins", "Clara Day", "Briana Ellis" ], "Active" : true, "DOB" : "Thu Sep 21 2000 06:45:36 GMT+0000 (UTC)", "Age" : 44 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ec"), "Name" : "Levine Johnston", "Address" : { "StreetNumber" : 737, "streetName" : "McKinley Avenue", "city" : "Helen", "state" : "DE", "ZIPCode" : 48935 }, "Friends" : [ "Willis Morrow", "Sutton Massey", "Gibson Herrera" ], "Active" : false, "DOB" : "Sat Jan 09 1988 00:04:53 GMT+0000 (UTC)", "Age" : 44 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e9"), "Name" : "Patrick Thornton", "Address" : { "StreetNumber" : 152, "streetName" : "Newkirk Avenue", "city" : "Summertown", "state" : "ID", "ZIPCode" : 95160 }, "Friends" : [ "Small Barlow", "Carson Cherry", "Leah Sherman", "Joyner Buckner" ], "Active" : true, "DOB" : "Sat Jan 12 2002 19:11:20 GMT+0000 (UTC)", "Age" : 25 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e7"), "Name" : "Sandy Oneil", "Address" : { "StreetNumber" : 819, "streetName" : "Pierrepont Place", "city" : "Gibsonia", "state" : "AR", "ZIPCode" : 10032 }, "Friends" : [ "Lorraine Berry", "Ola Brewer", "Sharlene Franklin", "Melanie Lynn", "Chandra Gilliam" ], "Active" : false, "DOB" : "Thu Aug 07 1980 05:30:31 GMT+0000 (UTC)", "Age" : 41 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834eb"), "Name" : "Susan Graham", "Address" : { "StreetNumber" : 797, "streetName" : "Doone Court", "city" : "Frierson", "state" : "IL", "ZIPCode" : 15612 }, "Friends" : [ "Clark Sharpe", "Guerra Goodman", "Vinson Jones", "Swanson Avery", "Socorro Morse" ], "Active" : false, "DOB" : "Fri Mar 17 1972 15:42:29 GMT+0000 (UTC)", "Age" : 41 }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ea"), "Name" : "Workman Holloway", "Address" : { "StreetNumber" : 955, "streetName" : "Covert Street", "city" : "Sylvanite", "state" : "GA", "ZIPCode" : 95335 }, "Friends" : [ "Sullivan Blackwell", "Ratliff Mccray" ], "Active" : false, "DOB" : "Thu Nov 12 1981 05:06:00 GMT+0000 (UTC)", "Age" : 29 }
```
* (Q3) List only the `id` and `name`s sorted in reverse alphabetical order by `name` (Z-to-A).
```text
> db.contacts.find({},{_id:1, Name:1}).sort({Name:-1})
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ea"), "Name" : "Workman Holloway" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834eb"), "Name" : "Susan Graham" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e7"), "Name" : "Sandy Oneil" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e9"), "Name" : "Patrick Thornton" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ec"), "Name" : "Levine Johnston" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ef"), "Name" : "Hayes Weaver" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ee"), "Name" : "Craft Parks" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e8"), "Name" : "Cooke Schroeder" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ed"), "Name" : "Aimee Mcintosh" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e6"), "Name" : "Aguirre Fox" }
```
* (Q4) Is the comparison of the attribute `name` case-sensitive? 
  Show how you try this with the previous query and include your answer.
```text
> db.contacts.find({},{_id:1, name:1}).sort({Name:-1})
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ea") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834eb") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e7") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e9") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ec") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ef") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ee") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e8") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ed") }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e6") }

This example does not print the attribute "Name" when we call  it as "Name".

> db.contacts.find({},{_id:1, Name:1}).sort({name:-1})
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e6"), "Name" : "Aguirre Fox" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e7"), "Name" : "Sandy Oneil" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e8"), "Name" : "Cooke Schroeder" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e9"), "Name" : "Patrick Thornton" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ea"), "Name" : "Workman Holloway" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834eb"), "Name" : "Susan Graham" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ec"), "Name" : "Levine Johnston" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ed"), "Name" : "Aimee Mcintosh" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ee"), "Name" : "Craft Parks" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ef"), "Name" : "Hayes Weaver" }

In this example we get the name attribute output when we call it with "Name"; 
However, this time we called sort with lowercase "name". So, output was not 
sorted.

Conclusion, Attributes are case-sensitive.
```
* (Q5) Repeat Q3 above but do not show the _id field.
```text
> db.contacts.find({},{_id:0, Name:1}).sort({Name:-1})
{ "Name" : "Workman Holloway" }
{ "Name" : "Susan Graham" }
{ "Name" : "Sandy Oneil" }
{ "Name" : "Patrick Thornton" }
{ "Name" : "Levine Johnston" }
{ "Name" : "Hayes Weaver" }
{ "Name" : "Craft Parks" }
{ "Name" : "Cooke Schroeder" }
{ "Name" : "Aimee Mcintosh" }
{ "Name" : "Aguirre Fox" }
```
* (Q6) Insert the following document to the collection.
```text
> db.contacts.insert({Name: {First: "David", Last: "Bark"}})
```
* (Q7) Where do you expect the new record to be located in the sort order? 
  Verify the answer and explain.
```text
I expect the new record to be located in at the beginning in the descending 
sort order. Because, the new record is an object and objects are at lower 
level in accenting data type order.

Verification:
> db.contacts.find({},{_id:1, Name:1}).sort({Name:-1})
{ "_id" : ObjectId("60ba798acc2fe4473d5e8cbc"), "Name" : { "First" : "David", "Last" : "Bark" } }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ea"), "Name" : "Workman Holloway" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834eb"), "Name" : "Susan Graham" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e7"), "Name" : "Sandy Oneil" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e9"), "Name" : "Patrick Thornton" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ec"), "Name" : "Levine Johnston" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ef"), "Name" : "Hayes Weaver" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ee"), "Name" : "Craft Parks" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e8"), "Name" : "Cooke Schroeder" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ed"), "Name" : "Aimee Mcintosh" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e6"), "Name" : "Aguirre Fox" }


```
* (Q8) Where do you expect the new document to appear in the sort order. 
  Verify your answer and explain after running the query.
```text
I expect new record to appear between "Craft Parks" and "Hayes Weaver", 
because we are using descending order with means ">" so we will take the 
higher of the array to compare, which is "David".

Verification:
> db.contacts.find({},{_id:1, Name:1}).sort({Name:-1})
{ "_id" : ObjectId("60ba798acc2fe4473d5e8cbc"), "Name" : { "First" : "David", "Last" : "Bark" } }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ea"), "Name" : "Workman Holloway" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834eb"), "Name" : "Susan Graham" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e7"), "Name" : "Sandy Oneil" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e9"), "Name" : "Patrick Thornton" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ec"), "Name" : "Levine Johnston" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ef"), "Name" : "Hayes Weaver" }
{ "_id" : ObjectId("60ba7bc050f7b6f352d22781"), "Name" : [ "David", "Bark" ] }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ee"), "Name" : "Craft Parks" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e8"), "Name" : "Cooke Schroeder" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ed"), "Name" : "Aimee Mcintosh" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e6"), "Name" : "Aguirre Fox" }
```

* (Q9) Where do you expect the last inserted record, {Name: ["David", "Bark"]}
  to appear this time? Does it appear in the same position relative to the 
  other records? Explain why or why not.

```text
I expect the last inserted record to appear between "Aimee Mcintosh" and 
"Cooke Schroeder", which is not the same position compared to descending order. In accending order, "<", we take the smallest of the array, which is "Bark", to compare.

Verification:
> db.contacts.find({},{_id:1, Name:1}).sort({Name:1})
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e6"), "Name" : "Aguirre Fox" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ed"), "Name" : "Aimee Mcintosh" }
{ "_id" : ObjectId("60ba7bc050f7b6f352d22781"), "Name" : [ "David", "Bark" ] }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e8"), "Name" : "Cooke Schroeder" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ee"), "Name" : "Craft Parks" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ef"), "Name" : "Hayes Weaver" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ec"), "Name" : "Levine Johnston" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e9"), "Name" : "Patrick Thornton" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834e7"), "Name" : "Sandy Oneil" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834eb"), "Name" : "Susan Graham" }
{ "_id" : ObjectId("60ba727d18ca7cc2a8a834ea"), "Name" : "Workman Holloway" }
{ "_id" : ObjectId("60ba798acc2fe4473d5e8cbc"), "Name" : { "First" : "David", "Last" : "Bark" } }
```

* (Q10) Build an index on the Name field for the users collection. Is MongoDB able to build the index on that field with the different value types stored in the Name field?

```text
> db.contacts.createIndex({Name:1})
{
	"createdCollectionAutomatically" : false,
	"numIndexesBefore" : 1,
	"numIndexesAfter" : 2,
	"ok" : 1
}

Yes, MongoDB is able to build the index on the Name field with the diffirent calue types.

Verification:
> db.contacts.getIndexes()
[
	{
		"v" : 2,
		"key" : {
			"_id" : 1
		},
		"name" : "_id_",
		"ns" : "db.contacts"
	},
	{
		"v" : 2,
		"key" : {
			"Name" : 1
		},
		"name" : "Name_1",
		"ns" : "db.contacts"
	}
]
```
