package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;
import static uk.emarte.regurgitator.core.Log.getLog;
import static uk.emarte.regurgitator.core.StringType.stringify;

public class RemoveAtIndexProcessor extends CollectionProcessor {
    private static final Log log = getLog(RemoveAtIndexProcessor.class);

    private final ValueSource indexSource;

    public RemoveAtIndexProcessor(ValueSource indexSource) {
        this.indexSource = indexSource;
    }

    @Override
    public Object processCollection(Collection<?> collection, Message message) throws RegurgitatorException {
        List<Object> list = new ArrayList<>(collection);
        Object indexObj = indexSource.getValue(message, log);
        int index = parseInt(stringify(indexObj));
        log.debug("Removing at index '{}'", index);
        list.remove(index);
        return list;
    }
}
