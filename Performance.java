import java.util.Scanner;

public class Performance{
    private int marks[];

    public Performance(){//this is the constructor we declared
        marks = new int[10];
    }

    public void readMarks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER MARKS FOR 10 STUDENTS: ");
        for(int i = 0;i<marks.length;i++){
            marks[i] = scanner.nextInt();
            if(marks[i]<0 || marks[i]>100){//validation
                System.out.println("Enter between 0 and 100");
                marks[i]--;
            }

        }
    }

    public int highestMark(){//we use int since we are returning an integer
        int maxMark = marks[0];
        for (int i = 1; i<marks.length; i++){
            if(marks[i]>maxMark){
                maxMark = marks[i];
            }
        }
        return maxMark;
    }
    public int leastMark() {
        int minMark = marks[0];

        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < minMark) {
                minMark = marks[i];
            }
        }

        return minMark;
    }
    public int getMode() {
        int mode = marks[0];
        int freq_max = 0;

        for (int i = 0; i < marks.length; i++) {
            int currMark = marks[i];
            int currFrequency = 1;

            for (int j = i + 1; j < marks.length; j++) {
                if (marks[j] == currMark) {
                    currFrequency++;
                }
            }

            if (currFrequency > freq_max || (currFrequency == freq_max && currMark > mode)) 
            {
                mode = currMark;
                freq_max = currFrequency;
            }
        }

        return mode;
    }
    public int getFreq() {
        int mode = getMode();
        int freq = 0;

        for (int i = 0; i < marks.length; i++) {
            if (marks[i] == mode) {
                freq++;
            }
        }

        return freq;
    }


    public void display(){
        System.out.println("Highest: " +highestMark());
        System.out.println("Frequency of Mode: " + getFreq());
        System.out.println("Least: " +leastMark());
        System.out.println("Mode: " + getMode());
        
        }
    

    public static void main(String[] args) {
        Performance markChecker = new Performance();
        markChecker.readMarks();
        markChecker.display();

    }
}