package Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface ModuleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Module module);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModules(Module... modules);

    @Query("SELECT * " +
            "FROM module " +
            "WHERE reference LIKE  :reference")
    LiveData<List<Module>> findModuleByReference(String reference);

    @Query("SELECT *" +
            " FROM module" +
            " ORDER BY reference ASC")
    LiveData<List<Module>> getAllModules();

    @Update
    void update(Module modules);

    @Update
    void updateModules(Module... modules);

    @Delete
    void delete(Module module);

    @Delete
    void deleteModules(Module... modules);
}
