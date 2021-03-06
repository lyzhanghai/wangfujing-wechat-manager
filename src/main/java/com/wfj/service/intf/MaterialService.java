package com.wfj.service.intf;

import java.util.List;
import java.util.Map;

import com.wfj.dto.ArticleDto;
import com.wfj.dto.MaterialDto;
import com.wfj.dto.MediaDto;

public interface MaterialService {
	public MediaDto materialInsert(String filePath, String type, String title, String introduction);

	public String articleInsert(List<ArticleDto> artList);

	public String imageInsert(String path, String param);

	public MaterialDto getMaterialList(int start, int limit, String type);

	public Map<String, Object> getMaterialByMediaId(String mediaId);

	public String materialDelete(String mediaId);
}
