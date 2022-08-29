

import java.util.*;
import java.math.BigInteger;
import java.lang.Math;

public class SRPN {
    private String StackNum1;
    private String StackNum2;
    private String last_result;
    private String ResultInStack;
    private long num1;
    private long num2;
    Stack<String> InputNum = new Stack<>();

 //Constructor
    public SRPN() {
    }

//Process operators(+-*/=) and operand(r,d) and comments(#)
//Comments(#) are put inside default statement,
// because this will put all the elements behind a "#" together.
//In default statement,using Userin.startwith to separate comments.
//Using try-catch to separate single line string and separate line string.
//Using try-catch to get error messages from each operation.
    public void CommandProcessor(String UserIn){
        switch(UserIn){
            case"+":
                try{
            Sum_meth();}
            catch(RuntimeException e){
               System.out.println("Stack underflow");
            }
            break;

            case"-":
                try{
            Sub_meth();}
                catch(RuntimeException e){
                    System.out.println("Stack underflow");
                }
            break;

            case"=":
            Equal_meth();
            break;

            case"*":
                try{
            Multi_meth();}
                catch(RuntimeException e){
                    System.out.println("Stack underflow");
                }
            break;

            case"/":
                try{
            Divide_meth();}
                catch(RuntimeException e){
                    System.out.println("Stack underflow");
                }
            break;

            case"%":
                try{
            Remain_meth();}
                catch(RuntimeException e){
                    System.out.println("Stack underflow");
                }
            break;

            case"d":
            d_meth();
            break;

            case"r":
            r_meth();
            break;

            case"^":
                try{
            power_meth();}
                catch(RuntimeException e){
                    System.out.println("Stack underflow");
                }
            break;
//Separate comments and operands.
            default:
            if (UserIn.startsWith("#")) {
                String[] character = UserIn.split("");
                for (int i = 0; i < character.length; i++) {
                    System.out.println(String.format("Unrecognised operator or operand \"%s\".", character[i]));
                }
            }
 //Check if string is all number.
 //If not, catch statement will past the string with operators to StringProcessor.
            try{
                Integer Check_digit=Integer.valueOf(UserIn);
                MakeStack(UserIn);
            }
            catch(NumberFormatException e) {
                StringProcessor(UserIn);
               }
            break;
        }
    }

//Single line string is put inside StringProcessor function to separate the operands and operators.
//Using if statement so the operator input can be judge by each if statement.
    public void StringProcessor(String UserIn){
        String[] HorizontalNum = UserIn.split("\s*([-+/*%^=])\s*");
        for (String split1:HorizontalNum){
            InputNum.push(split1);

        }

        if (UserIn.contains("=")) {
            try {
                String last_result1 = InputNum.peek();
                System.out.println(last_result1);
            } catch (EmptyStackException e) {
                System.out.println("Stack is empty");
            } }

        if (UserIn.contains("+")){
            try{
                Sum_meth();}
           catch(RuntimeException e){
               System.out.println("Stack underflow");
             }}

        if (UserIn.contains("-")){
            try{
                Sub_meth();}
            catch(RuntimeException e){
                System.out.println("Stack underflow");
            }
        }
        if (UserIn.contains("*")){
            try{
                Multi_meth();}
            catch(RuntimeException e){
                System.out.println("Stack underflow");
            }
        }
        if (UserIn.contains("/")) {
            try{
                Divide_meth();}
            catch(RuntimeException e) {
                System.out.println("Stack underflow");
            }}
        if (UserIn.contains("d")) {
            if (InputNum.isEmpty()){
                int d= Integer.MIN_VALUE;
                System.out.println(d);
            }
            else{
                Iterator element=InputNum.iterator();
                while(element.hasNext()){
                    System.out.println(element.next());}
            }
            }
        if (UserIn.contains("r")) {
                r_meth();
            }
        if (UserIn.contains("%")){
            try{
                Remain_meth();}
            catch(RuntimeException e){
                System.out.println("Stack underflow");
            }
        }
        if (UserIn.contains("^")){
            try{
                power_meth();}
            catch(RuntimeException e){
                System.out.println("Stack underflow");}
        }
        }

//MakeStack method to push all the operands from commandProcessor to a stack.
    public Stack MakeStack(String number) {
            InputNum.push(number);
            return InputNum;}

//Set the upper and lower bounds of the results after operations.
    public String Bound_Int(String resultInStack) {
        String Max = Integer.toString(Integer.MAX_VALUE);
        String Min = Integer.toString(Integer.MIN_VALUE);
//Use BigInteger class and Instantiate the object with the results from the stack.
        BigInteger res = new BigInteger(resultInStack);
        BigInteger maxInt = new BigInteger(Max);
        BigInteger minInt = new BigInteger(Min);
//Using BigInteger compare method to check the number is in upper bound or lower bound.
        if (res.compareTo(maxInt) == 1) {
            return Max;
        }
        else if (res.compareTo(minInt) == -1) {
            return Min;
        }
        return resultInStack;}

//"="method peeks and prints the last number in the stack
    public void Equal_meth(){
        try{
        last_result=InputNum.peek();
        PrintResult();}
        catch(EmptyStackException e){
            System.out.println("Stack is empty");
        }
    }


//Pop two elements from top of the stack.
//Convert to Long type so that allows more digits.
//After the addition convert the result back to String which will allow the number stay the same,
//At last, push the result back to the stack.
    public void Sum_meth(){
        if (InputNum.size()>1){
        StackNum1=InputNum.pop();
        StackNum2=InputNum.pop();
        num1=Long.valueOf(StackNum1);
        num2=Long.valueOf(StackNum2);
        ResultInStack=Long.toString(num1+num2);}
        MakeStack(Bound_Int(ResultInStack));
        }



//Pop two elements from top of the stack.
//Convert to Long type so that allows more digits.
// After the subtraction, convert the result back to String which will allow the number stay the same.
//At last, push the result back to the stack.
    public void Sub_meth(){
        if (InputNum.size()>1){
            StackNum1=InputNum.pop();
            StackNum2=InputNum.pop();
            num1=Long.valueOf(StackNum1);
            num2=Long.valueOf(StackNum2);
        ResultInStack=Long.toString(num2-num1); }
        MakeStack(Bound_Int(ResultInStack)); }


 //Pop two elements from top of the stack.
//Convert to Long type so that allows more digits.
//After the multiplication convert the result back to String which will allow the number stay the same,
//At last, push the result back to the stack.
    public void Multi_meth(){
        if (InputNum.size()>1){
            StackNum1=InputNum.pop();
            StackNum2=InputNum.pop();
            num1=Long.valueOf(StackNum1);
            num2=Long.valueOf(StackNum2);
            ResultInStack=Long.toString(num1*num2);}
            MakeStack(Bound_Int(ResultInStack)); }

 //Pop two elements from top of the stack.
//Convert to Long type so that allows more digits.
// After the division convert the result back to String which will allow the number stay the same,
//At last, push the result back to the stack.
    public void Divide_meth(){
        try{
            StackNum1=InputNum.pop();
            StackNum2=InputNum.pop();
            num1=Long.valueOf(StackNum1);
            num2=Long.valueOf(StackNum2);
        ResultInStack=Long.toString(num2/num1);
        MakeStack(Bound_Int(ResultInStack)); }
    catch(ArithmeticException e){
        System.out.println("Divide by 0.");
    }}


//Pop two elements from top of the stack.
//Convert to Long type so that allows to process more digits.
//The remainder does not need to test if within the the bounds or not.
//At last, push the remainder back to the stack.
    public void Remain_meth(){
        try{
            if (InputNum.size()>1){
                StackNum1=InputNum.pop();
                StackNum2=InputNum.pop();
                num1=Long.valueOf(StackNum1);
                num2=Long.valueOf(StackNum2);
                ResultInStack=Long.toString(num2%num1);}
            MakeStack(ResultInStack); }
        catch(ArithmeticException e){
            System.out.println("Divide by 0.");
        } }

//When letter "d" is called, firstly, if statement will check the stack is empty or not.
//If the stack is empty, d will be assigned the minimum value of the Integer.
//Otherwise d returns what is in the stack.
    public void d_meth(){
        if (InputNum.isEmpty()){
            int d= Integer.MIN_VALUE;
            System.out.println(d);
        }
        else{
        Iterator element=InputNum.iterator();
        while(element.hasNext()){
            System.out.println(element.next());}
        }

    }

 //Using nextInt method from Random class to get a random number.
 //When "r"is called, r will be assigned an arbitrary value from 0 to the maximum of the Integer.
    public void r_meth(){
        Random r=new Random();
        int randomInt;
        randomInt=r.nextInt(Integer.MAX_VALUE);
        MakeStack(Integer.toString(randomInt));
        System.out.println(randomInt);
    }


//When "^" is called, first check if the stack has at least 2 numbers.
//If the stack has two numbers,do the calculation.
//Using if statement to check the sign of the power.
//At last put the result back to the stack.
    public void power_meth(){
        if (InputNum.size()>1){
            StackNum1=InputNum.pop();
            StackNum2=InputNum.pop();
            num1=Long.valueOf(StackNum1);
            num2=Long.valueOf(StackNum2);
        if (num1>0){
        long poWer=(long) Math.pow(num2,num1);
        ResultInStack=Long.toString(poWer);
        MakeStack(Bound_Int(ResultInStack));}
        else{
        System.out.println("Negative power.");
        String back1=Long.toString(num1);
        String back2=Long.toString(num2);
        InputNum.push(back2);
        InputNum.push(back1);}

        } }



//A separate method for printing result so that the result will print only after "="
    public void PrintResult(){
        System.out.println(last_result);
    }
}

