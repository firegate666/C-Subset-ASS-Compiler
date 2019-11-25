package de.mk.exception;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Administrator
 * <p>
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SystemInfo {
    private Vector infos = new Vector();

    public SystemInfo() {
        //Enumeration result = v.elements();
        infos.add(
                "Java Runtime Environment version:"
                        + System.getProperty("java.version"));
        infos.add(
                "Java Runtime Environment vendor:"
                        + System.getProperty("java.vendor"));
        infos.add(
                "Java installation directory:" + System.getProperty("java.home"));
        infos.add(
                "Java Virtual Machine specification version:"
                        + System.getProperty("java.vm.specification.version"));
        infos.add(
                "Java Virtual Machine specification vendor:"
                        + System.getProperty("java.vm.specification.vendor"));
        infos.add(
                "Java Virtual Machine specification name:"
                        + System.getProperty("java.vm.specification.name "));
        infos.add(
                "Java Virtual Machine implementation version:"
                        + System.getProperty("java.vm.version"));
        infos.add(
                "Java Virtual Machine implementation vendor :"
                        + System.getProperty("java.vm.vendor"));
        infos.add(
                "Java Virtual Machine implementation name :"
                        + System.getProperty("java.vm.name"));
        infos.add(
                "Java Runtime Environment specification version:"
                        + System.getProperty("java.specification.version"));
        infos.add(
                "Java Runtime Environment specification vendor :"
                        + System.getProperty("java.specification.vendor"));
        infos.add(
                "Java Runtime Environment specification name :"
                        + System.getProperty("java.specification.name "));
        infos.add(
                "Java class format version number :"
                        + System.getProperty("java.class.version"));
        infos.add("Java class path:" + System.getProperty("java.class.path"));
        infos.add("Operating system name :" + System.getProperty("os.name"));
        infos.add(
                "Operating system architecture :" + System.getProperty("os.arch"));
        infos.add(
                "Operating system version:" + System.getProperty("os.version"));
        infos.add("File separator :" + System.getProperty("file.separator"));
        infos.add("Path separator :" + System.getProperty("path.separator"));
        infos.add("Line separator :" + System.getProperty("line.separator"));
        infos.add("User's account name :" + System.getProperty("user.name"));
        infos.add("User's home directory :" + System.getProperty("user.home "));
        infos.add(
                "User's current working directory :"
                        + System.getProperty("user.dir"));
    }

    public Enumeration getInfos() {
        return infos.elements();
    }

    public String getInfosAsString() {
        String result = "";
        Enumeration e = this.getInfos();

        while (e.hasMoreElements()) {
            result += e.nextElement() + "\n";
        }
        return result;
    }

}
