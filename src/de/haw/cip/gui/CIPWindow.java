package de.haw.cip.gui;

import de.haw.cip.logic.Symboltable;
import de.haw.cip.logic.scanner.Scanner;
import de.mb.swing.JDefaultFrame;
import de.mb.swing.JNumberedTextArea;
import de.mb.swing.JStandardStatusBar;
import de.mk.exception.CompilerErrorException;
import de.mk.swing.ScrollText;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CIPWindow extends JDefaultFrame {

    private File _sourceFile;
    private JNumberedTextArea _sourceText;

    private JTabbedPane _outputTabbedPane;
    private JNumberedTextArea _scannerOutput;
    //private JNumberedTextArea _symbolOutput;
    private JSymboltable _symbolOutput;
    private JNumberedTextArea _ptfOutput;
    private JNumberedTextArea _asmOutput;

    private ScrollText statusPanel;
    private JPanel split;
    private JPanel mainpanel;
    private Scanner s = null;
    private int lastOpenedFiles = 0;
    private CIPMenu menu;

    public CIPWindow() throws CompilerErrorException {
        super();
        initialize();
    }

    public void setPtfOutput(String s) {
        _ptfOutput = new JNumberedTextArea(s.trim());
        _outputTabbedPane.remove(2);
        _outputTabbedPane.add(_ptfOutput.getPane(), 2);
        _outputTabbedPane.setTitleAt(2, "3. PTF-Output");
    }

    public void setSymbolOutput(Symboltable s) {
        _symbolOutput = new JSymboltable(s);
        _outputTabbedPane.remove(1);
        _outputTabbedPane.add(new JScrollPane(_symbolOutput)/*.getPane()*/, 1);
        _outputTabbedPane.setTitleAt(1, "2. Symboltabelle");
        //Prt.ln("NEW SYMBOLTABLE");
    }

    public void setScannerOutput(String s) {
        _scannerOutput = new JNumberedTextArea(s.trim());
        _outputTabbedPane.remove(0);
        _outputTabbedPane.add(_scannerOutput.getPane(), 0);
        _outputTabbedPane.setTitleAt(0, "1. Scanner-Output");
    }

    public void setAsmOutput(String s) {
        _asmOutput = new JNumberedTextArea(s.trim());
        _outputTabbedPane.remove(3);
        _outputTabbedPane.add(_asmOutput.getPane(), 3);
        _outputTabbedPane.setTitleAt(3, "4. ASM-Output");
    }

    /**
     * Returns the String content of selected output textfield.
     *
     * @return String
     */
    public String getSelectedSource() {
        int index = _outputTabbedPane.getSelectedIndex();
        switch (index) {
            case 0:
                return this._scannerOutput.getText();
            case 1:
                return this._symbolOutput.getText();
            case 2:
                return this._ptfOutput.getText();
            case 3:
                return this._asmOutput.getText();
            default:
                return "";
        }
    }

    /**
     * Return the Scanner Object
     */
    public Scanner getScanner() throws CompilerErrorException {
        if (s == null)
            throw new CompilerErrorException("Automaton has not been initalized. Please run lexical analysis first.");
        return s;
    }

    /**
     * Initializes the internal Scanner with new Scanner
     */
    public void setScanner() {
        s = new Scanner();
        s.setApp(this);
    }

    public String getDestText() {
        return _scannerOutput.getText();
    }

    public File getSourceFile() {
        return _sourceFile;
    }

    public void setSourceFile(File f) {
        _sourceFile = f;
    }

    public String getSourceText() {
        return this._sourceText.getText();
    }

    public void setSourceText(String s) {
        _sourceText.setText(s);
    }

    private ScrollText getScrollableTextPanel(
            String string,
            boolean autoscroll,
            boolean editable) {
        ScrollText result = new ScrollText(string);

        result.setAutoscrolls(autoscroll);

        return result;
    }

    public void clearOutputs() {
        this.setScannerOutput("");
        this.setSymbolOutput(new Symboltable());//_symbolOutput = new JSymboltable(new Symboltable());
        this.setPtfOutput("");
        this.setAsmOutput("");
//		_outputTabbedPane.remove( 1);
//		_outputTabbedPane.add(new JScrollPane(_symbolOutput)/*.getPane()*/,1);
//		_outputTabbedPane.setTitleAt(1, "2. Symboltabelle");
    }

    private JPanel getTextPanel() {

        _sourceText = new JNumberedTextArea("source");
        _scannerOutput = new JNumberedTextArea();
        _symbolOutput = new JSymboltable(new Symboltable());
        _ptfOutput = new JNumberedTextArea();
        _asmOutput = new JNumberedTextArea();
        split = new JPanel();
        split.setLayout(new GridLayout(1, 1));
        split.add(_sourceText.getPane());

        _outputTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        _outputTabbedPane.add(_scannerOutput.getPane(), 0);
        _outputTabbedPane.setTitleAt(0, "1. Scanner-Output");
        _outputTabbedPane.add(new JScrollPane(_symbolOutput)/*.getPane()*/, 1);
        _outputTabbedPane.setTitleAt(1, "2. Symboltabelle");
        _outputTabbedPane.add(_ptfOutput.getPane(), 2);
        _outputTabbedPane.setTitleAt(2, "3. PTF-Output");
        _outputTabbedPane.add(_asmOutput.getPane(), 3);
        _outputTabbedPane.setTitleAt(3, "4. ASM-Output");

        split.add(_sourceText.getPane(), 0);
        split.add(_outputTabbedPane, 1);

        return split;
    }


    private JPanel getStatusPanel() {
        statusPanel = getScrollableTextPanel("", true, true);
        JPanel result = new JPanel(new BorderLayout());
        result.add(statusPanel.getPane(), BorderLayout.CENTER);
        return result;
    }

    public void printStatus(String statusLine) {
        this.statusPanel.setText(
                this.statusPanel.getText() + "\n- " + statusLine + "\n");
    }

    public void resetStatus() {
        this.statusPanel.setText("");
    }

    private void initialize() throws CompilerErrorException {
        menu = new CIPMenu(this);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(menu.getMyMenuBar(), BorderLayout.NORTH);
        getContentPane().add(getTextPanel(), BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());

        south.add(getStatusPanel(), BorderLayout.CENTER);
        south.add(new JStandardStatusBar(), BorderLayout.SOUTH);

        getContentPane().add(south, BorderLayout.SOUTH);

        this.setTitle(appTitle()); //$NON-NLS-1$
    }

    public String appTitle() {
        return Ressource_Bundle.getString("app_name");
    }

    public String appVersion() {
        return Ressource_Bundle.getString("app_version");
    }

    public String appAuthors() {
        return Ressource_Bundle.getString("app_authors");
    }

    public boolean hasLastOpenedFiles() {
        return lastOpenedFiles != 0;
    }

    public CIPMenu getMenu() {
        return menu;
    }

    public void addLastOpenedFile(File f) {
        menu.addLastOpenedFile(f, lastOpenedFiles);
    }

    public void switchSourceVisible() {
        boolean visible = this._sourceText.isVisible();
        if (visible) {
            split.remove(this._sourceText);
        } else {
            split.add(this._sourceText, 0);
        }
        this._sourceText.setVisible(!visible);
        this.menu.selectViewSource(visible);
        split.updateUI();
    }

    public void switchOutputVisible() {
        boolean visible = this._outputTabbedPane.isVisible();
        if (visible) {
            split.remove(this._outputTabbedPane);
        } else {
            split.add(this._outputTabbedPane);
        }
        this._outputTabbedPane.setVisible(!visible);
        this.menu.selectViewOutput(visible);
        split.updateUI();
    }

    public void switchStatusVisible() {
        boolean visible = this.statusPanel.isVisible();
        this.statusPanel.setVisible(!visible);
        this.menu.selectViewStatus(visible);
        this.validate();
    }


}
