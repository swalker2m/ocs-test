package edu.gemini.spModel.core;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Peer implements Serializable {

    /** Hostname, never null.  */
    public final String host;

    /** Port. */
    public final int port;

    /** Site, or null if no affiliation. */
    public final Site site;

    /**
     * Construct a new Peer.
     * @param host hostname, cannot be null
     * @param port port
     * @param site site affiliation, or null
     */
    public Peer(String host, int port, Site site) {
        if (host == null) throw new IllegalArgumentException("host cannot be null");
        this.host = host;
        this.port = port;
        this.site = site;
    }

    /**
     * Construct a new Peer with no site.
     * @param host hostname, cannot be null
     * @param port port
     */
    public Peer(String host, int port) {
        this(host, port, null);
    }

    @Override
    public String toString() {
        return String.format("%s:%s%s", host, port, site == null ? "" : ":" + site.name());
    }

    private static final Pattern PAT = Pattern.compile("([^,]+)\\:(\\d+)(\\:(G[NS]))?");

    public String displayName() {
        return (site == null) ? String.format("%s:%s", host, port) : site.displayName;
    }

    public static Peer tryParse(String s) {
        final Matcher m = PAT.matcher(s);
        if (m.matches()) {
            final String hostStr = m.group(1);
            final int port       = Integer.parseInt(m.group(2));
            final Site site      = Site.tryParse(m.group(4));
            return new Peer(hostStr, port, site);
        } else {
            return null;
        }
    }

    @Override // GENERATED BY IDEA
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peer peer = (Peer) o;
        if (port != peer.port) return false;
        if (host != null ? !host.equals(peer.host) : peer.host != null) return false;
        return site == peer.site;
    }

    @Override // GENERATED BY IDEA
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        result = 31 * result + (site != null ? site.hashCode() : 0);
        return result;
    }

}
