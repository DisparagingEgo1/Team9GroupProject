//Group 9
//Ocean Oestreicher, Andrew Korchemniy, Tyler Wang
package cs350s22.component.ui.parser;

import cs350s22.support.Identifier;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Command {
    private final String[] commandText;
    private int tokenIndex;

    public Command(final String[] commandText) {
        this.commandText = commandText;
        this.tokenIndex = 0;
    }

    /**
     * @return The next token
     * @throws ArrayIndexOutOfBoundsException If the next token does not exist
     */
    public String getNext() throws ArrayIndexOutOfBoundsException {
        return commandText[tokenIndex++];
    }

    /**
     * Returns the token from commandText at the specified index.
     *
     * @param index Index of token to retrieve
     * @return The token at index
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds
     */
    public String tokenAt(int index) throws ArrayIndexOutOfBoundsException {
        return commandText[index];
    }

    public int length() {
        return commandText.length;
    }

    public boolean hasNext() {
        return this.tokenIndex < commandText.length;
    }

    /**
     * Gets the next token and removes double quotes from beginning and end.
     * Throws a RuntimeException if token is not surrounded by double quotes.
     *
     * @return Filepath trimmed of double quotes
     */
    public String getNextFilepath() {
        String path = getNext();
        if (path.startsWith("\"") && path.endsWith("\"")) {
            path = path.replaceAll("^\"|\"$", "");
        } else {
            throw new RuntimeException("Invalid Command <string> Entered: Not Delimited By Double Quotes");
        }
        return path;
    }

    /**
     * @return String array containing the remaining tokens
     */
    public String[] collateRemaining() {
        String[] res = Arrays.copyOfRange(commandText, tokenIndex, commandText.length);
        tokenIndex = commandText.length;
        return res;
    }

    /**
     * Collates all tokens until a specified token is found.
     * Throws a RuntimeException if no terminating token is not found and not doing remaining.
     *
     * @param terminators          Tokens to terminate before
     * @param doRemainingOtherwise To collate remaining if no terminator is found
     * @return String array containing the specified tokens
     */
    public String[] collateTo(String[] terminators, boolean doRemainingOtherwise) {
        for (int i = tokenIndex; i < commandText.length; i++) {
            for (String terminator : terminators) {
                if (commandText[i].equalsIgnoreCase(terminator) ) {
                    String[] res = Arrays.copyOfRange(commandText, tokenIndex, i);
                    tokenIndex = i;
                    return res;
                }
                else if(i == commandText.length - 1 && doRemainingOtherwise){
                    String[] res = Arrays.copyOfRange(commandText, tokenIndex, i+1);
                    tokenIndex = i+1;
                    return res;
                }
            }
        }
        throw new RuntimeException("Invalid Command Entered: Unexpected Argument Count");
    }

    public String getCurrentToken() {
        return commandText[tokenIndex - 1];
    }

    public boolean containsQuotes(String token) {
        return token.contains("\"") || token.contains("'");
    }

    public Identifier getIdentifier(final String id) {
        if (containsQuotes(id))
            throw new RuntimeException("Invalid Command Entered: Unexpected Quotes");
        return Identifier.make(id);
    }

    /**
     * @param ids Identifier strings
     * @return Identifier objects
     */
    public List<Identifier> getIdentifiers(final String[] ids) {
        List<Identifier> identifiers = new LinkedList<>();
        for (String id : ids) {
            if (containsQuotes(id))
                throw new RuntimeException("Invalid Command Entered: Unexpected Quotes");
            identifiers.add(Identifier.make(id));
        }
        return identifiers;
    }


}
