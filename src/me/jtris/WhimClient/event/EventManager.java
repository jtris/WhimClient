package WhimClient.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class EventManager {

    private static final Map<Class<? extends Event>, ArrayList<EventData>> REGISTRY_MAP = new HashMap<Class<? extends Event>, ArrayList<EventData>>();

    private static void sortListValue(final Class<? extends Event> class_)
    {
        final ArrayList<EventData> flexibleArray = new ArrayList<EventData>();

        for (final byte b : EventPriority.VALUE_ARRAY) {
            for (EventData methodData : EventManager.REGISTRY_MAP.get(class_)) {
                if (methodData.priority == b) {
                    flexibleArray.add(methodData);
                }
            }
        }

        EventManager.REGISTRY_MAP.put(class_, flexibleArray);
    }

    /* the method must have exactly one param. and must have annotation*/
    private static boolean isMethodBad(final Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
    }

    /* additionally, the method's first param. type cannot be class_*/
    private static boolean isMethodBad(final Method method, final Class<? extends Event> class_) {
        return isMethodBad(method) || method.getParameterTypes()[0].equals(class_);
    }

    /* returns an ArrayList of events by class */
    public static ArrayList<EventData> get(final Class<? extends Event> class_) {
        return REGISTRY_MAP.get(class_);
    }

    /* cleans map values */
    public static void cleanMap(final boolean removeOnlyEmptyValues) {
        final Iterator<Map.Entry<Class<? extends Event>, ArrayList<EventData>>> iterator = EventManager.REGISTRY_MAP.entrySet().iterator();

        while (iterator.hasNext()) {
            if (!removeOnlyEmptyValues || iterator.next().getValue().isEmpty()) {
                iterator.remove();
            }
        }
    }

    /* unregisters a class */
    public static void unregister(final Object o, final Class<? extends Event> class_) {
        if (REGISTRY_MAP.containsKey(class_)) {
            for (final EventData methodData : REGISTRY_MAP.get(class_)) {
                if (methodData.source.equals(o)) {
                    REGISTRY_MAP.get(class_).remove(methodData);
                }
            }
        }

        cleanMap(true);
    }

    public static void unregister(final Object o) {
        for (ArrayList<EventData> flexibleArray : REGISTRY_MAP.values()) {
            for (int i = flexibleArray.size() - 1; i >= 0; i--) {

                if (flexibleArray.get(i).source.equals(o)) {
                    flexibleArray.remove(i);
                }
            }
        }

        cleanMap(true);
    }

    public static void register(final Method method, final Object o) {

        final Class<?> class_ = method.getParameterTypes()[0];

        final EventData methodData = new EventData(o, method, method.getAnnotation(EventTarget.class).value());

        if (!methodData.target.isAccessible()) { // <- deprecated
        //if (!methodData.target.canAccess(o)) {
            methodData.target.setAccessible(true);
        }

        if (REGISTRY_MAP.containsKey(class_)) {
            if (!REGISTRY_MAP.get(class_).contains(methodData)) {
                REGISTRY_MAP.get(class_).add(methodData);
                sortListValue((Class<? extends Event>) class_);
            }
        }
        else {
            REGISTRY_MAP.put((Class<? extends Event>) class_, new ArrayList<EventData>() {
                {
                    this.add(methodData);
                }
            });
        }
    }

    /* registers a class to the map */
    public static void register(final Object o, final Class<? extends Event> class_) {

        for (final Method method : o.getClass().getMethods()) {
            if (!isMethodBad(method, class_)) {
                register(method, o);
            }
        }
    }

    public static void register(Object o) {
        for (final Method method : o.getClass().getMethods()) {
            if (!isMethodBad(method)) {
                register(method, o);
            }
        }
    }

}

