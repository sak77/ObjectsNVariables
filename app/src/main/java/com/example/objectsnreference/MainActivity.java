package com.example.objectsnreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //objectInitialization();
        //variableInitialization();
        //objectspassByReference();
        usingEqualsToCompareObjects();
    }

    //Objects are stored in Heap memory
    //Variables are stored in Stack memory

    //Stack memory works in LIFO manner. So when a variable is not required, it is popped from the stack.
    //Heap does not have such a mechanism. Hence it periodically runs a garbage collector to clean up
    //un-required memory to free up space.

    //When we initialize an object, it creates a pointer to a address in the heap memory where the object is stored.
    //Whereas with variables, it creates a new entry in the stack memory. Like a new copy maybe.

    Car mercedes = new Car("Mercedes", "Silver", 2019);

    //Assigning a object to another object does NOT create a separate copy of the object in the memory.
    //instead it adds a new reference pointer to the same memory location.

    Car myTempCar = mercedes;

    //In above case both myTempCar and mercedes refer to the same Car object in heap memory

    private void objectInitialization() {
        //Both references mercedes and myTempCar refer to same Car object in heap memory. Hence both logs will output same text.
        Log.i(TAG, "Mercedes color - " + mercedes.getColor());
        Log.i(TAG, "Temp Car color - " + myTempCar.getColor());

        //Any update via one of the references will also reflect when other pointer pulls the same information from the object
        myTempCar.setColor("Black");

        //Now both references will return the updated color
        Log.i(TAG, "Mercedes color - " + mercedes.getColor());
        Log.i(TAG, "Temp Car color - " + myTempCar.getColor());

        /*IMPORTANT - Setting a object reference to null DOES NOT delete the object from the heap memory.
        It only deletes reference to that object. If an object in memory does not have any references,
        it becomes an ideal candidate to be cleaned up the next time the garbage collector runs.
        */
        myTempCar = null;   //This does not delete the Car object in the memory, It only deletes the reference myTempCar to the CAr Object.
        //However, since mercedes still points to the same car object, it can still be used to get its values or perform any operations on it.
        Log.i(TAG, "Mercedes color - " + mercedes.getColor());

        //If we also set mercedes to null, then the Car object has no more references and it may be cleaned up
        //Next time gc runs.
    }

    private void variableInitialization() {
        //Variables are saved to the stack memory
        //each new instance is a new copy in the stack

        int age = 10;   //New instance in stack with value 10

        int age2 = age; //Creates new instance in stack called age2 which is copy of age. So both have same value.

        //Update age2
        age2 = 12;  //This only updates the value of age2 and not the original age variable.

        Log.i(TAG, "Age 1 - " + age);
        Log.i(TAG, "Age 2 - " + age2);
    }

    private void mutableNimmutableObjects() {
        //Objects can be mutable (updatable) or immutable(not updateable or like final)
        //Mutable objects only guarantee that the object of the reference cannot be changed,
        //however other references within that object may be mutable, for values in a final array can still be updated.

        //String is an example of an immutable object. It is a final class in the java doc.
        //Other immutable objects in the jdk are Integer, Double, Float, Long, Boolean, BigDecimal.
        String car_name = "Volvo";  //This creates an instance of String with value "Volvo"

        //Now if we assign a new value to the string, then what happens??
        //This basically creates a new String object in the heap memory and makes car_name point to the new String object
        //Which also probably means that the original string object "Volvo" has no reference and hence may
        //be collected by the gc the next time it runs on the heap.
        car_name = "Volvo2";

        //Suppose we assign one string to another, then it means we have 2 pointers to the same string
        //instance.
        String tempCarName = car_name;  //Here both variables are pointing to the same memory address.

        /*
        Check out the answer in this link for more clear explanation -
        https://stackoverflow.com/questions/24346787/repeatedly-updating-string-in-java/24346811
         */

        /*
        Immutable instances are useful when working with multiple threads which work with same value. It
        prevents values getting accidently updated later on.
         */
    }

    private void mutableNimmutableVariables() {
        //In Java all variables are mutable by default. So their value can be changed after they are created.
        //However, if we declare the variable as final, then its value CANNOT change. Thus it becomes immuttable.

        int i = 14; //Mutable variable i
        i = 15;     //This will update value of the same variable in memory as it is mutable. No new entry will be pushed to the stack.

        final int j = 10;   //This creates a immutable entry in the stack memory.
    }


    private void objectspassByReference() {
        /*Although objects are accessed via reference/pointers to the memory location.
        But when they are passed as parameters to a function, they are passed by value.
        Which means that the original reference to the object is not passed as is to the function.

        Instead, the function creates a new reference that points to the original object.
        This reference is valid till the scope of the function. Since it points to the same object, it can be
        used to access, update values of the object.

        */

        Car myCar = new Car("Toyota", "Blue", 2009);
        /*
        Here myCar is a mutable object, So any updated done by setCarColorRed() will impact the original car object
        in the memory. Which in turn will also affect myCar, since it references same Car object in memory.
         */
        setCarColorRed(myCar);
        Log.i(TAG, "myCar color is " + myCar.getColor());

        /*
        However, for immutable objects like String. When they are passed to the function as parameter.
        Then any update done by the function, will NOT impact the original String.
         */
        String color1 = myCar.getColor();
        updateColorGreen(color1);

        Log.i(TAG, "Color 1 - " + color1);
    }

    /*
    Objects are passed by value. So paramCar is a new pointer to the same object pointed as by myCar.
     */
    private void setCarColorRed(Car paramCar) {
        //myCar is a new reference to the same Car object. Its scope is local to this function.
        paramCar.setColor("Red");
    }

    private void updateColorGreen(String color_name) {
        color_name = "Green";
        Log.i(TAG, "Color - " + color_name);
    }


    private void usingEqualsToCompareObjects() {
        //Objects can be compared using .equals() method.
        /*
        Default implementation fo .equals() is same as ==.
        Which means it simply checks if both object references point to the same object in memory.
        So in below case both comparisions are true.
         */
        Car myCar1 = new Car("Toyota", "Red", 1999);

        Car myCar2 = new Car("Toyota", "Red", 1999);

        //Car myCar2 = myCar1;

        //Here values of both objects are compared hence it returns true.
        if (myCar1.equals(myCar2)) {
            Log.i(TAG, "Both objects are equal");
        } else {
            Log.i(TAG, "Objects do not match");
        }

        if (myCar1 == myCar2) {
            Log.i(TAG, "Objects are ==");
        } else {
            Log.i(TAG, "Objects are not ==");
        }

        /*
        However, .equals() can be overriding to provide its own comparision logic. In which case,
        it can match individual properties of each object variable
         */

        /*
            hashcode() returns a unique id for each object. Object references should ideally be compared via
            hashcode() and only if it matches, then can use equals(). If you are overriding .equals() method
            then you must override hashcode() as well.
         */

        int code1 = myCar1.hashCode();
        int code2 = myCar2.hashCode();

        Log.i(TAG, "Car 1 hashcode - " + code1 + ", car2 hashcode  - " + code2);

    }
}