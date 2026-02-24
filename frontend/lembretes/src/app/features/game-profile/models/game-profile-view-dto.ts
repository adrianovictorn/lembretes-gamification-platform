import { UserSimpleViewDTO } from "../../user/models/user-view.dto";

export interface GameProfileViewDTO {
    id: string;
    user: UserSimpleViewDTO;
    xp:number;
    moedas: number;
    streakAtual: number;
    melhorStreak: number;
    ultimoDiaAtivo: Date;
    totalConcluido: number;
    criadoEm: string;
    atualizadoEm: string;
}