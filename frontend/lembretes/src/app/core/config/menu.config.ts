import { BookText, CirclePlus, List, LucideIconData, PlusIcon, User } from "lucide-angular";

export type Role = 'ROLE_ADMIN'| 'ROLE_USER';



export interface MenuItem {
    label: string
    path: string
    children: MenuItem[]
    icon: LucideIconData
    role: Role[]
    
}

export const MENU_ITEMS: MenuItem[] = [
     {
            label: "Profile",
            path: "/profile",
            children: [],
            icon: User,
            role: ['ROLE_ADMIN', 'ROLE_USER']
        },
    {
        label: 'Lembrete', 
        path: '/lembretes', 
        children: [
            {
                label: 'Novo',
                path: '/input',
                role: ['ROLE_USER', 'ROLE_ADMIN'],
                icon: CirclePlus,
                children: []
            },
            {
                label: 'Histórico',
                path: '/lembrete/historico',
                role: ['ROLE_ADMIN', 'ROLE_USER'],
                icon: List,
                children: []
            }
        ],
        icon: BookText,
        role: ['ROLE_ADMIN', 'ROLE_USER']}
       
]

