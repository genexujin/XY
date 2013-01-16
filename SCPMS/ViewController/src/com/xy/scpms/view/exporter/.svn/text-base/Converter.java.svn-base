package com.xy.scpms.view.exporter;


import com.outsideinsdk.Export;
import com.outsideinsdk.ExportStatusCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.security.InvalidParameterException;

import java.util.Properties;
import java.util.StringTokenizer;


public class Converter {

    private static final String INPUTPATHKEY = "inputpath";
    private static final String OUTPUTPATHKEY = "outputpath";
    private static final String OUTPUTIDKEY = "outputid";


    private static Properties configProps = new Properties();

    public Converter() throws FileNotFoundException, IOException {

    }

    static {

        try {

            //  Assure the configuration file exists and is readable.
            File cff = new File("E:\\OM\\xy\\imageConverter\\ix.cfg");
//            File cff = new File("c:\\xy\\imageConverter\\ix.cfg");

            if (!cff.exists() || !cff.isFile() || !cff.canRead()) {
                throw (new InvalidParameterException("Configuration file unreadable."));
            }

            BufferedReader cfr = new BufferedReader(new FileReader(cff));

            String line;
            //  Loop over all lines from the file.
            while ((line = cfr.readLine()) != null) {
                processLine(line);
            }
        } catch (InvalidParameterException ipe) {
            // TODO: Add catch code
            ipe.printStackTrace();
        } catch (IOException ioe) {
            // TODO: Add catch code
            ioe.printStackTrace();
        }


    }


    /**
     *  Support the parsing of the configuration file by processing a given
     *  line.
     *
     *  @param l  A line from a configuration file.
     */
    private static void processLine(String l) {
        //  Look for comments.
        int indPound = l.indexOf('#');

        //  Remove comments and whitespace.
        String line =
            (indPound == -1) ? l.trim() : l.substring(0, indPound).trim();

        if (line.length() != 0) {
            StringTokenizer stl = new StringTokenizer(line);
            String key = stl.nextToken();
            String value = stl.nextToken();
            while (stl.hasMoreTokens()) {
                value += " " + stl.nextToken();
            }
            //  Fill in the appropriate property.
            configProps.setProperty(key, value);
        }
    }

    /**
     *  Run the conversion using the given input path, output path.
     *
     *  @param ifp     Input path.
     *  @param ofp     Output path.
     *  @param timeout Export process timeout in milliseconds.
     */
    public void convert(String ifp, String ofp, long timeout) {
        try {
            String oid = configProps.getProperty(OUTPUTIDKEY);

            //  Display the parameters.
            System.out.println("Input Path: " + ifp + " Output Path: " + ofp +
                               " Output ID: " + oid);

            //  Remove extra control properties.
            configProps.remove(INPUTPATHKEY);
            configProps.remove(OUTPUTPATHKEY);

            //  Create list of input files.
            File iff = new File(ifp);
            File[] iffa;
            if (iff.isDirectory())
                iffa = iff.listFiles();
            else {
                iffa = new File[1];
                iffa[0] = iff;
            }

            //  Create output directory if needed.  Assuming that if the input path
            //  is a directory, the output path should also be a directory.
            File off = new File(ofp);
            if (iff.isDirectory() && !off.exists())
                off.mkdir();

            //  Process the conversion.
            Export e = new Export(configProps);
            if (off.isDirectory()) {
                //  The outputid is in the form fi_XXX where XXX is a reasonable
                //  extension so we take the extension for the oid.
                //  oid.substring(3) means to get the string following the fi_
                String ext = "." + oid.substring(3);
                for (int i = 0; i < iffa.length; i++) {
                    String ifn = iffa[i].toString();
                    String ofn =
                        ofp + File.separator + iffa[i].getName() + ext;
                    System.out.println("Converting " + ifn + " to " + ofn);
                    ExportStatusCode result =
                        e.convert(ifn, ofn, oid, timeout);
                    if (result.getCode() ==
                        ExportStatusCode.SCCERR_OK.getCode()) {
                        System.out.println("Conversion Successful!");
                    } else {
                        System.out.println("Conversion Error: " + result);
                    }
                }
            } else {
                for (int i = 0; i < iffa.length; i++) {
                    ExportStatusCode result =
                        e.convert(iffa[i].toString(), ofp, oid, timeout);
                    if (result.getCode() ==
                        ExportStatusCode.SCCERR_OK.getCode()) {
                        System.out.println("Conversion Successful!");
                    } else {
                        
                        System.out.println("Conversion Error: " + result.getCode());
                    }
                }
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int count = args.length;

        Converter ct = null;

        try {
            ct = new Converter();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        long timeout = 0;
        if (count == 4) {
            timeout = Integer.decode(args[3]).longValue();
        }

        ct.convert("c:\\xy\\upload\\raw\\affffffff\\affffffff.doc", "c:\\xy\\upload\\converted\\affffffff\\affffffff.gif",
                   timeout);
    }

}
