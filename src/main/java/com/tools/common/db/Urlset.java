package com.tools.common.db;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Xue.
 * @program: tools
 * @create 2019-04-19 18:16
 * @des 描述：
 */
@XmlRootElement
public class Urlset {
    private List<Url> url = new ArrayList<>();

    @XmlRootElement
    public static class Url {
        private String loc;
        private String lastmod;
        private String changefreq;
        private String priority;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getLastmod() {
            return lastmod;
        }

        public void setLastmod(String lastmod) {
            this.lastmod = lastmod;
        }

        public String getChangefreq() {
            return changefreq;
        }

        public void setChangefreq(String changefreq) {
            this.changefreq = changefreq;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }
    }

    public List<Url> getUrl() {
        return url;
    }

    public void setUrl(List<Url> url) {
        this.url = url;
    }
}
