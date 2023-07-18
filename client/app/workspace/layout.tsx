export default function WorkspaceLayout({
    children,
}: {
    children: React.ReactNode
}) {
    return (
        <>
            <aside>
                <ul>
                    <li>
                        <a href={`/workspace`}>get all jobs</a>
                    </li>
                    <li>
                        <a href={`/workspace/post-job`}>post a job</a>
                    </li>
                    <li>
                        <a href={`/workspace/user-jobs`}>get specific jobs use has created</a>
                    </li>
                    <li>
                        <a href={`/workspace/user-bids`}>get specific jobs user has bid on</a>
                    </li>
                </ul>
            </aside>
            <main>
                {children}
            </main>
        </>
    )
}