package cs350s22.component.ui.parser;

import cs350s22.component.actuator.A_Actuator;
import cs350s22.component.controller.A_Controller;
import cs350s22.component.sensor.A_Sensor;
import cs350s22.support.Identifier;

import java.util.List;

public class BuildParser {

    protected static void networkParse(final A_ParserHelper ph, final Command cmd){
        List<Identifier> identifiers;
        List<A_Controller> controllers;
        List<A_Actuator> actuators;
        List<A_Sensor> sensors;

        try {
            if (!(cmd.getNext().equalsIgnoreCase("WITH") &&
                    cmd.getNext().toUpperCase().matches("COMPONENTS?")))
                throw new RuntimeException("Invalid BUILD NETWORK Command");
            if (!cmd.hasNext())
                throw new RuntimeException("Invalid BUILD NETWORK Command: No Components Found");

            // Obtain components
            identifiers = cmd.getIdentifiers(cmd.collateRemaining());
            controllers = ph.getSymbolTableController().get(identifiers, true);
            actuators = ph.getSymbolTableActuator().get(identifiers, true);
            sensors = ph.getSymbolTableSensor().get(identifiers, true);

            // Add components to master
            ph.getControllerMaster().addComponents(controllers);
            ph.getControllerMaster().addComponents(actuators);
            ph.getControllerMaster().addComponents(sensors);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Invalid BUILD NETWORK Command Entered: Unexpected Argument Count");
        }
    }
}
