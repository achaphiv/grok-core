package grok.core;

import java.util.Date;

public class RouteUpdate {

    private final String clientId;
    private final Date date;
    private final Route route;

    private RouteUpdate(String clientId, Date date, Route route) {
        this.clientId = clientId;
        this.date = date;
        this.route = route;
    }

    public static RouteUpdate of(String clientId, Date date, Route route) {
        return new RouteUpdate(clientId, date, route);
    }
}
