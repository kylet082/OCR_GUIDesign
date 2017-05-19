# OCR using OpenCV and Encog

## This project was an experimental / crash course on OCR. Primarily to explore Neural Networks and touch a little on Computer vision basics. The outcome, has developed into a full user interface using Swing :/ and JTattoo look and feel theme. Below is the GUI design and a few functionalities the program is capable of. 

Dependencies:

•	OpenCV v3.2.0 - Java bindings

•	Encog-core v3.3.0 - (Machine Learning API – A fantastic library)

•	JTattoo v1.6.11 (Look and feel theme – the short cut to trying to make it look pretty)

•	JFreeCharts v1.0.19 

## GUI Design

![gui](https://cloud.githubusercontent.com/assets/14908229/26211640/dbcfd042-3bea-11e7-9d6b-be72e5828479.PNG)

## Imaging Functions

#### Used OpenCV for most imaging manipulation methods.At the moment the only imaging funtions that can manually be used to alter images are:  
## Adjusting Contrast

   The test image with bad illumination qualities:
    
   ![contrast image](https://cloud.githubusercontent.com/assets/14908229/26211899/97b7663a-3beb-11e7-93b8-96bbfd8f1a7e.PNG)
    
       Using Histogram Equalization:
    
   ![equal](https://cloud.githubusercontent.com/assets/14908229/26212156/8d56e142-3bec-11e7-90ab-00609a1300ea.PNG)
    
        Contrast sketching: 
    
   ![cont](https://cloud.githubusercontent.com/assets/14908229/26212248/d9f9451c-3bec-11e7-8a54-79e53df01401.PNG)
   
## Reducing Noise by applying guassian blur:
    
      The image before applying function
    
![noise balloon](https://cloud.githubusercontent.com/assets/14908229/26212922/1f938004-3bef-11e7-87bb-57b32b0e0fa9.PNG)

    The after effects, the image is smoother but is not as sharp.
    
![blur after](https://cloud.githubusercontent.com/assets/14908229/26213031/6793d372-3bef-11e7-9b11-f6aa7d66a96c.PNG)

## Finding the text in the Image 
  
      The first step is to straighten the image or check that it is straight. This has been done using the Hough Line 
      transformation. The resulting line can be used to find the angle of the document skew using linear gradient 
      equation. 
  
![skew doc](https://cloud.githubusercontent.com/assets/14908229/26213417/de7c9cc0-3bf0-11e7-9f13-0489cbcc35e5.PNG)
  
      The Resulting line projection. The image is passed through Canny edge detection to find relavant pixels for the 
      Hough algorithm. The document image can now be straighted.  
  
![hlresult](https://cloud.githubusercontent.com/assets/14908229/26213459/09893112-3bf1-11e7-9d8e-fdedbea1f1b8.PNG)  

#### Finding text bodies
   
      Using a method called projection Profiling, which calculates the pixel density along the X-axis. The bins 
      along the y axis are used to find the textlines. The result is plotted as a Hstogram to visualise the 
      relationship between textlines and bins.
   
   ![projection profile](https://cloud.githubusercontent.com/assets/14908229/26213698/e8aef41c-3bf1-11e7-922f-8ebe5953ef3a.PNG)
   
       The histogram data is then used to sample down the text lines by finding the start and end points along the
       y and x axis.
         
   ![textlines](https://cloud.githubusercontent.com/assets/14908229/26214662/1d1a5a0e-3bf5-11e7-93cf-293543aee513.PNG)

### Word Segmeting Coming soon!

### Character segmentation Coming Soon!

### Feature Extraction and creating the training sets

      Using a method called temple matching to create protoypes of the learnt font set. These Randsom note 
      letters will be used to train the selected Neural Network. Template matching is probably the least robust
      method for character recognition, but is easy to implement. An example of the previewed character sets:
      
![templatematching](https://cloud.githubusercontent.com/assets/14908229/26244186/a94921ae-3c86-11e7-9ba7-4709ef5df5f0.PNG)

### Creating and configuring the neural network
      
      I tried to automate the creaton of the neural networks as much as possible. So the number of input, hidden and output
      nuerons is calculated using the size of sample training data (matrix width and height) and the number of symbols in
      the training sets. 
      
![netconfig](https://cloud.githubusercontent.com/assets/14908229/26244423/7d5a6aa2-3c87-11e7-9f2b-92c0cf30664d.PNG)
