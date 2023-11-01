import java.util.ArrayList;
import java.util.Date;
class Vulnerability{
    public CVE cve;
}
class Configuration{
    public ArrayList<Node> nodes;
}

class CpeMatch{
    public boolean vulnerable;
    public String criteria;
    public String matchCriteriaId;
    public String versionEndIncluding;
}

public class CVE implements Comparable<CVE>{
    public String id;
    public String sourceIdentifier;
    public Date published;
    public Date lastModified;
    public String vulnStatus;
    public ArrayList<Description> descriptions;
    public Metrics metrics;
    public ArrayList<Weakness> weaknesses;
    public ArrayList<Configuration> configurations;
    public ArrayList<Reference> references;
    public String evaluatorSolution;
    public String evaluatorImpact;

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int compareTo(CVE o) {

        double this_base_score = 0;
        double other_base_score = 0;
        double this_impact_score = 0;
        double other_impact_score = 0;
        double this_exp_score = 0;
        double other_exp_score = 0;

        try {
            this_base_score = this.metrics.cvssMetricV2.getFirst().cvssData.baseScore;
        } catch (Exception ignore) {}

        try {

            other_base_score = o.metrics.cvssMetricV2.getFirst().cvssData.baseScore;
        } catch (Exception ignore) {}

        try {

            this_impact_score = o.metrics.cvssMetricV2.getFirst().impactScore;
        } catch (Exception ignore) {}

        try {

            other_impact_score = o.metrics.cvssMetricV2.getFirst().impactScore;
        } catch (Exception ignore) {}

        try {

            this_exp_score = this.metrics.cvssMetricV2.getFirst().exploitabilityScore;
        } catch (Exception ignore) {}

        try {

            other_exp_score = o.metrics.cvssMetricV2.getFirst().exploitabilityScore;
        } catch (Exception ignore) {}




        String this_cve_id = this.id;
        String other_cve_id = o.id;

        if (this_base_score > other_base_score) {
            return 1;
        } else if (this_base_score < other_base_score){
            return -1;
        }

        if (this_impact_score > other_impact_score) {
            return 1;
        } else if (this_impact_score < other_impact_score){
            return -1;
        }

        if (this_exp_score > other_exp_score) {
            return 1;
        } else if (this_exp_score < other_exp_score) {
            return -1;
        }

        if (this_cve_id.compareTo(other_cve_id) < 0) {
            return 1;
        } else if (this_cve_id.compareTo(other_cve_id) > 0) {
            return -1;
        }

        return 0;

    }
}

class CvssData{
    public String version;
    public String vectorString;
    public String accessVector;
    public String accessComplexity;
    public String authentication;
    public String confidentialityImpact;
    public String integrityImpact;
    public String availabilityImpact;
    public double baseScore;
}

class CvssMetricV2{
    public String source;
    public String type;
    public CvssData cvssData;
    public String baseSeverity;
    public double exploitabilityScore;
    public double impactScore;
    public boolean acInsufInfo;
    public boolean obtainAllPrivilege;
    public boolean obtainUserPrivilege;
    public boolean obtainOtherPrivilege;
    public boolean userInteractionRequired;
}

class Description{
    public String lang;
    public String value;
}

class Description2{
    public String lang;
    public String value;
}

class Metrics{
    public ArrayList<CvssMetricV2> cvssMetricV2;
}

class Node{
    public String operator;
    public boolean negate;
    public ArrayList<CpeMatch> cpeMatch;
}

class Reference{
    public String url;
    public String source;
    public ArrayList<String> tags;
}

class Root{
    public int resultsPerPage;
    public int startIndex;
    public int totalResults;
    public String format;
    public String version;
    public Date timestamp;
    public ArrayList<Vulnerability> vulnerabilities;
}


class Weakness{
    public String source;
    public String type;
    public ArrayList<Description> description;
}

