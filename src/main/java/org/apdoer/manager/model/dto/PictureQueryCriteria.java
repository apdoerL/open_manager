package org.apdoer.manager.model.dto;

import lombok.Data;

/**
 * sm.ms图床
 *
 * @author apdoer
 * @date 2019-6-4 09:52:09
 */
@Data
public class PictureQueryCriteria{

    private String filename;
    
    private String username;
}
