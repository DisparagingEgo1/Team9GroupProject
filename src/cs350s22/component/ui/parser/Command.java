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

    public boolean equalsNext(String toCompare) {
        return this.commandText[this.tokenIndex].equalsIgnoreCase(toCompare);
    }

    public boolean equalsNext(String[] toCompare) {
        for (String s : toCompare) {
            if (this.equalsNext(s)) return true;
        }
        return false;
    }

    public boolean isNumeric(String candidate) {
        try {
            Double.parseDouble(candidate);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

    public String[] collateRemaining() {
        for (int i = tokenIndex; i < commandText.length; i++) {
            if (containsQuotes(commandText[i]))
                throw new RuntimeException("Invalid Command Entered: Unexpected Quotes");
        }
        String[] res = Arrays.copyOfRange(commandText, tokenIndex, commandText.length);
        tokenIndex = commandText.length;
        return res;
    }

    /**
     * Collates all tokens until specified token is found.
     * Throws a RuntimeException if the terminating token is not found or if a quote is found.
     *
     * @param terminator Token to terminate before
     * @return String array containing the specified tokens
     */
    public String[] collateTo(String terminator) {
        for (int i = tokenIndex; i < commandText.length; i++) {
            if (containsQuotes(commandText[i]))
                throw new RuntimeException("Invalid Command Entered: Unexpected Quotes");
            if (commandText[i].equalsIgnoreCase(terminator)) {
                String[] res = Arrays.copyOfRange(commandText, tokenIndex, i);
                tokenIndex = i;
                return res;
            }
        }
        throw new RuntimeException("Invalid Command Entered: Unexpected Argument Count");
    }

    /**
     * Collates all tokens until a specified token is found.
     * Throws a RuntimeException if no terminating token is not found or if a quote is found.
     *
     * @param terminators Tokens to terminate before
     * @return String array containing the specified tokens
     */
    public String[] collateTo(String[] terminators) {
        for (int i = tokenIndex; i < commandText.length; i++) {
            if (containsQuotes(commandText[i]))
                throw new RuntimeException("Invalid Command Entered: Unexpected Quotes");
            for (String terminator : terminators) {
                if (commandText[i].equalsIgnoreCase(terminator)) {
                    String[] res = Arrays.copyOfRange(commandText, tokenIndex, i);
                    tokenIndex = i;
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

    /**
     * @param ids Identifier strings
     * @return Identifier objects
     */
    public List<Identifier> getIdentifiers(final String[] ids) {
        List<Identifier> identifiers = new LinkedList<>();
        for (String id : ids) {
            identifiers.add(Identifier.make(id));
        }
        return identifiers;
    }

    /**
     * @param table      The table to check within for Identifiers
     * @param components The list of identifiers to check for existence
     * @return True if all identifiers exist in the table
     */
    public boolean identifiersExist(final SymbolTable<?> table, final List<Identifier> components) {
        for (Identifier id : components) {
            if (!table.contains(id)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param table The table with the components
     * @param identifiers The Ids of the components
     * @return The list of the components from the table
     * @param <T> The type of the table
     */
    public <T> List<T> getComponents(final SymbolTable<T> table, final List<Identifier> identifiers){
        List<T> components = new LinkedList<>();
        for(Identifier id: identifiers){
            components.add(table.get(id));
        }
        return components;
    }

}
