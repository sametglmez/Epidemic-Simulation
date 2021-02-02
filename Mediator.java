import java.awt.*;
import java.util.ArrayList;

/**
 * Mediator Pattern
 * for transactions between people
 */
public class Mediator {
    ArrayList<Person> persons;
    TimeForCollison timeForCollison;
    TimeForCollison timeForCollison1;
    int flag;

    /**
     * Constructor
     */
    public Mediator() {
        this.persons = new ArrayList<>();
        this.flag = 0;

    }

    /**
     * Find intersection between persons
     * @param person
     * @param index
     * @return
     */
    public int findInterSection(Person person, int index){
        Rectangle rec1 = new Rectangle();
        Rectangle rec2 = new Rectangle();
        int resultFlag = 0;
        int cMax = 0;
        int dMin = 0;

        if(person.collusionFlag == true){
            return -1;
        }

        for(int i = 0 ; i < persons.size(); i++){
            if ( i == index  || persons.get(i).collusionFlag == true) {
                resultFlag = 1;
                break;
            }
           double firstPersonX = person.coordinateX;
           double firstPersonY = person.coordinateY;
           double firstPersonWidth= person.width;
           double firstPersonHeight = person.height;
           double secondPersonX = persons.get(i).coordinateX;
           double secondPersonY = persons.get(i).coordinateY;
           double secondPersonWidth = persons.get(i).height;
           double secondPersonHeight = persons.get(i).width;


            if (!((firstPersonX - firstPersonWidth/2 > secondPersonX + secondPersonWidth/2) ||
                    (firstPersonX + firstPersonWidth/2 < secondPersonX - secondPersonWidth/2) ||
                    (firstPersonY - firstPersonHeight/2 > secondPersonY + secondPersonHeight/2) ||
                    (firstPersonY + firstPersonHeight/2 < secondPersonY - secondPersonHeight/2))){

                persons.get(i).collusionFlag = true;
                person.collusionFlag = true;
                persons.get(i).collusionFlag2 = true;
                person.collusionFlag2 = true;



                if(person.C > persons.get(i).C)
                    cMax = person.C;
                else
                    cMax = persons.get(i).C;

                if(person.socialDistance > persons.get(i).socialDistance)
                    dMin =  persons.get(i).socialDistance;
                else
                    dMin =  person.socialDistance;

                persons.get(i).timerCollison.startTimer(4);
                person.timerCollison.startTimer(4);

                flag = 1;

            }

            if ( flag == 1){
                flag = 0;
                double p;

                p = (0.7 * (1 + cMax / 10) * person.maskStatus * persons.get(i).maskStatus * (1-dMin/10));
                if(p >  1 )
                     p = 1;

                if(person.getState() == person.injectedState){
                    //System.out.println("P değeri : " + p );
                    if(( p > 0 || p < 1)){
                        persons.get(i).injectedStateOccurance();
                    }

                }else if (persons.get(i).getState() == persons.get(i).injectedState){
                    if( p > 0 || p < 1){
                        person.injectedStateOccurance();
                    }
                }

                return i;
            }
        }

        return -1;
    }

}
