package com.ishortner.server.service.impl;

import com.ishortner.server.service.ServerService;
import com.ishortner.server.domain.Server;
import com.ishortner.server.repository.ServerRepository;
import com.ishortner.server.service.dto.ServerDTO;
import com.ishortner.server.service.mapper.ServerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Server}.
 */
@Service
@Transactional
public class ServerServiceImpl implements ServerService {
	
	static String[] countries = new String[] {"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
	static String[] browser = new String[] {"AMAYA", "ARORA", "AVANT", "CAMINO", "GOOGLE CHROME", "CYBERDOG", "DILLO", "EPIPHANY", "ELINKS", "FIREFOX", "GALEON", "GNUZILLA", "K-MELEON", "KAZEHAKASE", "KONQUEROR", "EDGE", "LYNUX", "MAXTHON", "INTERNET EXPLORER", "MIDORI", "MOZILLA", "WIKI", "NCSA", "NETSCAPE", "NETSURF", "OMNIWEB", "OPERA", "SAFARI", "SEAMINKEY", "W3M", "CHROMIUM"};

    private final Logger log = LoggerFactory.getLogger(ServerServiceImpl.class);

    private final ServerRepository serverRepository;

    private final ServerMapper serverMapper;

    public ServerServiceImpl(ServerRepository serverRepository, ServerMapper serverMapper) {
        this.serverRepository = serverRepository;
        this.serverMapper = serverMapper;
    }
    
    public ServerDTO generator(Long id) {
	    ServerDTO serverDto = new ServerDTO();
		int min = 0;
		int max = countries.length -1;
		int index = (int)(Math.random() * (max - min));
		String pos = countries[index];
		max = browser.length -1;
		index = (int)(Math.random() * (max - min));
		String bro = browser[index];
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String d = dateFormat.format(date);
		serverDto.setPosizione(pos);
		serverDto.setTipologia(bro);
		serverDto.setData(d);
		serverDto.setFkurl(id);
		log.debug("Server: "+serverDto.toString());
		serverRepository.save(serverMapper.toEntity(serverDto));
		return serverDto;
    }
    
    public ServerDTO getServerInfo(Long id) {
    	return serverMapper.toDto(serverRepository.findAllByFkurl(id));
    }

    @Override
    public ServerDTO save(ServerDTO serverDTO) {
        log.debug("Request to save Server : {}", serverDTO);
        Server server = serverMapper.toEntity(serverDTO);
        server = serverRepository.save(server);
        return serverMapper.toDto(server);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServerDTO> findAll() {
        log.debug("Request to get all Servers");
        return serverRepository.findAll().stream()
            .map(serverMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ServerDTO> findOne(Long id) {
        log.debug("Request to get Server : {}", id);
        return serverRepository.findById(id)
            .map(serverMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Server : {}", id);
        serverRepository.deleteById(id);
    }

    
}
