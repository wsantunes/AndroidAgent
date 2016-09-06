package org.ocsinventoryng.android.sections;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.format.DateFormat;

import org.ocsinventoryng.android.actions.OCSLog;

import java.util.ArrayList;

/*
 * 
     <ASSETTAG>Asset-1234567890</ASSETTAG>
      <BDATE>03/26/2008</BDATE>
      <BMANUFACTURER>American Megatrends Inc.</BMANUFACTURER>
      <BVERSION>0802</BVERSION>
      <MMANUFACTURER>ASUSTeK Computer INC.</MMANUFACTURER>
      <MMODEL>P5KPL-VM</MMODEL>
      <MSN>MT7084K06409125</MSN>
      <SMANUFACTURER>System manufacturer</SMANUFACTURER>
      <SMODEL>System Product Name</SMODEL>
      <SSN>System Serial Number</SSN>
 * 
 * 
 
BIOS (BDATE | BMANUFACTURER | BVERSION | SMANUFACTURER | SMODEL | SSN | TYPE | ASSETTAG)
*/
public class OCSBios implements OCSSectionInterface {

    final private String sectionTag = "BIOS";

    private String assettag;
    private String date;
    private String manufacturer;
    //private String smanufacturer;
    private String version;
    private String serial;
    private String type;
    private String smodel;

    private OCSLog ocslog;


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public OCSBios() {
        type = "Mobile";
        ocslog = OCSLog.getInstance();
        assettag = Build.ID + "-0123456789";
        date = (String) DateFormat.format("MM/dd/yy", Build.TIME);
        manufacturer = Build.MANUFACTURER;
        version = Build.BOOTLOADER;
        ocslog.debug("BIOS version: " + version);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            serial = Build.SERIAL;
        } else {
            SystemInfos.getInstance().getSerial();
        }
        ocslog.debug("OCSBIOS serial " + serial);
        smodel = Build.MODEL;
        ocslog.debug("OCSBIOS model: " + smodel);
    }
    /*
    <!ELEMENT BIOS (BDATE | BMANUFACTURER | BVERSION | SMANUFACTURER | SMODEL | SSN | TYPE | ASSETTAG)*>
	*/

    public OCSSection getSection() {
        OCSSection s = new OCSSection(sectionTag);
        s.setAttr("TYPE", type);
        s.setAttr("ASSETTAG", assettag);
        s.setAttr("BDATE", date);
        s.setAttr("BMANUFACTURER", manufacturer);
        s.setAttr("BVERSION", version);
        s.setAttr("MMANUFACTURER", manufacturer);
        s.setAttr("MSN", serial);
        s.setAttr("SMANUFACTURER", manufacturer);
        s.setAttr("SMODEL", smodel);
        s.setAttr("SSN", serial);
        s.setTitle(assettag);
        return s;
    }

    public ArrayList<OCSSection> getSections() {
        ArrayList<OCSSection> lst = new ArrayList<OCSSection>();
        lst.add(getSection());
        return lst;
    }

    public String toString() {
        return getSection().toString();
    }

    public String toXML() {
        return getSection().toXML();
    }

    public String getSectionTag() {
        return sectionTag;
    }
}
