import { Routes } from '@angular/router';
import { LoginFormPages } from './features/user/pages/form/login-form/login-form-page';
import { AppLayout } from './layout/app-layout/app-layout';


export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginFormPages},
    { 
        path: 'lembretes', component: AppLayout,
        children: [
            {
                path: '',
                loadComponent: () => import('./features/lembretes/pages/list/lembrete-list-page/lembrete-list-page').then(m => m.LembreteListPage)
            }
        ]},
    { path: 'lembrete/novo', component: AppLayout,
        children: [
            {
                path: '',
                loadComponent: () => import('./features/lembretes/pages/form/lembrete-form/lembrete-form-page').then(m => m.LembreteFormPage)
            }
        ]},
    {
        path: 'input', component: AppLayout,
        children: [
            {
                path: '',
                loadComponent: () => import('./features/input/components/input-form/input-form').then(m => m.InputForm)
            }
        ]
    },
    {
        path: 'profile', component: AppLayout,
        children: [
            {
                path: '',
                loadComponent: () => import('./features/game-profile/components/game-profile-view/game-profile-view').then(m => m.GameProfileView)
            }
        ]
    }
   
];
