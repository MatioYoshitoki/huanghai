package com.matio.services.intf;

import com.matio.pojo.Examine;
import com.matio.pojo.Mme;

import java.util.List;

/**
 * Created by matioyoshitoki on 2017/11/29.
 */
public interface IMmeService {
    public String marlboro(Examine examine);
    public String marlboro_batch(List<Examine> examines);
    public List<Mme> selectByLocus(String locus);
}
