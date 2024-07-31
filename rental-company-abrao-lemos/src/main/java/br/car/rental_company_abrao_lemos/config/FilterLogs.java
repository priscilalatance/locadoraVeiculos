package br.car.rental_company_abrao_lemos.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.MatchingFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

public class FilterLogs extends MatchingFilter {

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String string, Object[] os, Throwable thrwbl) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        if (level.isGreaterOrEqual(Level.INFO)) {
            return FilterReply.NEUTRAL;
        }
        return FilterReply.DENY;
    
    }

}
