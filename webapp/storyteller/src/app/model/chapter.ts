export interface Chapter{
    id: string;
    chapterTitle: string;
    content: string;
    lastModified: Date;
}

export interface AddChapterRequest{
    chapterTitle: string;
    content: string;
    lastModified: Date;
    bookId: string;
}

export interface UpdateChapterRequest{
    id: string;
    chapterTitle: string;
    content: string;
    lastModified: Date;
}
