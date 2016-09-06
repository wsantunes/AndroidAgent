package org.ocsinventoryng.android.agent;

import android.app.ListActivity;
import android.os.Bundle;

import org.ocsinventoryng.android.actions.Inventory;
import org.ocsinventoryng.android.actions.OCSLog;
import org.ocsinventoryng.android.sections.OCSSection;

import java.util.ArrayList;

public class OCSSectionListActivity extends ListActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        OCSLog ocslog = OCSLog.getInstance();

        Bundle b = getIntent().getExtras();
        if (b == null) {
            ocslog.debug("OCSSectionListActivity bundle null");
            return;
        }
        CharSequence section = b.getCharSequence("ocsinventory.section").toString();
        if (section == null) {
            return;
        }
        this.setTitle(section);

        // recuperation de la section
        ArrayList<OCSSection> asl = (ArrayList<OCSSection>) Inventory.getInstance(this).
                getSections(section.toString());
        if (asl == null) {
            return;
        }

        // Creation de l'adapteur avec la liste des sections
        SectionAdapter adapter = new SectionAdapter(this, asl);
        setListAdapter(adapter);
    }
} 