# Lab 7&8

## Student information
* Full name: Alp Can Aloglu
* E-mail: aalog001@ucr.edu
* UCR NetID: aalog001
* Student ID: 862190749

## Answers

* (Q1) In the README file, explain how you did it and include a code snippet that highlights the code that you added 
  to make it work. 
   

I changed the input columns of vectorAssembler to other examples.

val vectorAssembler = new VectorAssembler()
          .setInputCols(`Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot", "condition", "waterfront", "view")`)
          .setOutputCol("features")


* (Q2) Which one provided the best model for you?

```text
The following array of features gives the best correlation.

Array("bedrooms", "bathrooms", "condition", "waterfront", "view", "sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15")

RMSE on test set is 249647.2303278026

Correlation is [1.0                 0.7616653161724647  
0.7616653161724647  1.0                 ]

The fallowing array of features gives the best RMSE.

Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot", "condition", "waterfront", "view")

RMSE on test set is 230846.4428603467

Correlation is [1.0                 0.7391180468975547  
0.7391180468975547  1.0                 ]

If we consider difference in RMSE, it is minimal compare to other RSME we cot from other features. So it would be 
best to choose higher collation one between these two. So, the following array gives us the best result;

Array("bedrooms", "bathrooms", "condition", "waterfront", "view", "sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15")


```