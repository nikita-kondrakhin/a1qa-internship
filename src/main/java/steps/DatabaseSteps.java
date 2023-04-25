package steps;

import models.Test;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSteps {
    private DatabaseSteps() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", DatabaseSteps.class.getCanonicalName())); //todo getCanonicalName?
    }
    public static void getNexageProjectTestsList() {
        List<Test> testList = new ArrayList<>();

    }
}
