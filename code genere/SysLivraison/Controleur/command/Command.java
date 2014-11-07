package Controleur.command;

import java.util.*;

/**
 * 
 */
public interface Command {

    /**
     * 
     */
    public void execute();

    /**
     * 
     */
    public void undo();

    /**
     * 
     */
    public void redo();

}