import { LevelViewDTO } from "../../level/services/level-view.dto";
import { UserSimpleViewDTO } from "../../user/models/user-view.dto";

export interface GameProfileViewDTO {
    id: string;
    user: UserSimpleViewDTO;
    level: LevelViewDTO;
    xp:number;
    moedas: number;
    streakAtual: number;
    melhorStreak: number;
    ultimoDiaAtivo: Date;
    totalConcluido: number;
    criadoEm: string;
    atualizadoEm: string;
    role: string
}