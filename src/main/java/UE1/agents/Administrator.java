package UE1.agents;

import UE1.behaviours.StarterBehaviour;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import java.sql.SQLOutput;

/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class Administrator extends Agent {
    protected void setup(){
        System.out.println("I am an administrator!");
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("The oneshot behaviour works!");
            }
        });
        addBehaviour(new StarterBehaviour());
    }
}
