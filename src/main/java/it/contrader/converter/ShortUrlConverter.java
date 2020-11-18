package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.ShortUrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.ShortUrl;
import it.contrader.model.User;

@Component
public class ShortUrlConverter extends AbstractConverter<ShortUrl, ShortUrlDTO>{

	@Override
	public ShortUrl toEntity(ShortUrlDTO dto) {
		ShortUrl url = null;
		if (dto != null) {
			url = new ShortUrl(dto.getId(), dto.getLongurl(), dto.getShorturl(), dto.getFk_url());
		}
		return url;
	}

	@Override
	public ShortUrlDTO toDTO(ShortUrl entity) {
		ShortUrlDTO urlDTO = null;
		if (entity != null) {
			urlDTO = new ShortUrlDTO(entity.getId(), entity.getLongurl(), entity.getShorturl(), entity.getFkurl());

		}
		return urlDTO;
	}

}
