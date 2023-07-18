import { JobForm } from "./JobForm";

export default function PostJob() {
    return (
        <main className="flex min-h-screen flex-col items-center justify-center p-24">
            <div>this will be a form to post job - postJob()</div>
            <div>POST /api/jobs</div>
            <JobForm></JobForm>
        </main>
    )
}