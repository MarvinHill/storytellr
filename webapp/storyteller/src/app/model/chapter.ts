export interface Chapter{
    id: string;
    chapterTitle: string;
    content: string;
    lastModified: Date;
    bookId: string;
    published: boolean;
}

export interface AddChapterRequest{
    chapterTitle: string;
    content: string;
    lastModified: Date;
    bookId: string;
    published: boolean;
}

export interface EditChapterRequest{
    id: string;
    chapterTitle: string;
    content: string;
    lastModified: Date;
    bookId: string;
    published: boolean;
}
