import { MyButton } from './MyButton'

export default function Workspace() {
    return (
        <main className="flex min-h-screen flex-col items-center justify-center p-24">
            <MyButton>Random button</MyButton>
            <div>default workspace view - getAllJobs()</div>
            <div>GET /api/jobs</div>
        </main>
    )
}