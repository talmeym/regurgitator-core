package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
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
        Object indexObj = indexSource.getValue(message, log);

        if(indexObj != null) {
            try {
                int index = parseInt(stringify(indexObj));

                if (index >= 0 && index < collection.size()) {
                    List<Object> list = new ArrayList<>(collection);
                    log.debug("Removing index '{}' of value '{}'", index, list);
                    list.remove(index);
                    return list;
                }

                throw new RegurgitatorException(format("Index '%d' out of bounds for value '%s'", index, collection));
            } catch(NumberFormatException nfe) {
                throw new RegurgitatorException(format("Index value '%s' is not a number", stringify(indexObj)));
            }
        }

        throw new RegurgitatorException("Value for index could not be found");
    }
}
