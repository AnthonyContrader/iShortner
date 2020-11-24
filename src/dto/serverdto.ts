/**
 * Classe DTO di Server. DEVE essere uguale (stesso nome classe, stessi attributi e stessi nomi) a
 * quello nel backend. 
 * 
 */
export class ServerDTO {

    id: number;
 
    posizione: string;
 
    tipologia: string;
 
    data: string;

    fkIdUrl: number;
 
 }