/**
 * Classe DTO di Url.
 * DEVE essere uguale a quello nel backend. 
 */
 export class UrlDTO {

    id: number;

    longurl: string;
    
    shorturl: string;
    
	fkuser: number;
}