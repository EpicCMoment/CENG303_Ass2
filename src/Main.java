import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        File vuln_file = new File("vulns.json");

        ObjectMapper om = new ObjectMapper();
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Root json_root = om.readValue(vuln_file, Root.class);


        int cve_amount = 50_000;
        CVE[] cves = new CVE[cve_amount];

        for (int i = 0; i < cve_amount; i++) {
            cves[i] = json_root.vulnerabilities.getFirst().cve;
            json_root.vulnerabilities.removeFirst();
        }



        // you can test your code by this

        // createa a copy of the original array
        CVE[] quick_array = Arrays.copyOfRange(cves, 0, cves.length);

        // sort the copy array
        MergeSort.sort(quick_array);

        // print out the sorted array
        System.out.println(Arrays.toString(quick_array));



    }


}
