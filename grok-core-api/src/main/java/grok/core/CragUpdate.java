package grok.core;

import java.util.Date;

public class CragUpdate {

    private final String clientId;
    private final Date date;
    private final Crag crag;

    private CragUpdate(String clientId, Date date, Crag crag) {
        this.clientId = clientId;
        this.date = date;
        this.crag = crag;
    }

    public static CragUpdate of(String clientId, Date date, Crag crag) {
        return new CragUpdate(clientId, date, crag);
    }
}
